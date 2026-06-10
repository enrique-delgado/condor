package com.condor.transactionsengine.integration;

import com.condor.commons.util.DateTimeUtils;
import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsengine.dto.service.WebhookNotification;
import com.condor.transactionsengine.persistence.entity.AccountEntity;
import com.condor.transactionsengine.persistence.entity.TransactionEntity;
import com.condor.transactionsengine.persistence.repository.AccountRepository;
import com.condor.transactionsengine.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
@RequiredArgsConstructor
public class TransactionInitiatedProcessor {
    private static final Logger logger = LoggerFactory.getLogger(TransactionInitiatedProcessor.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final WebClient webClient;

    // distributed lock simulation (per accountId)
    private static final ConcurrentHashMap<Long, ReentrantLock> accountLocks = new ConcurrentHashMap<>();

    @Value("${webhook.url:}")
    private String webhookUrl;

    @Transactional
    public void process(TransactionInitiatedEventProto.TransactionInitiatedEvent message) {
        Long accountId = message.getAccountId();

        // distributed lock per account
        ReentrantLock lock = accountLocks.computeIfAbsent(accountId, id -> new ReentrantLock());
        lock.lock();

        try {
            AccountEntity account = accountRepository.findById(accountId).orElse(null);

            TransactionEntity tx = new TransactionEntity();
            tx.setId(message.getId());
            tx.setAmount(message.getAmount());
            tx.setRequestedAt(DateTimeUtils.fromEpochMillis(message.getRequestedAt()));
            tx.setCreatedAt(LocalDateTime.now());

            if (account == null) {
                tx.setAccountId(null);
                tx.setStatus("FAIL");
                tx.setErrorMessage(String.format("Account %s not found", accountId));
            } else if (account.getBalance() + message.getAmount() < 0) {
                // withdrawal validation
                tx.setAccountId(accountId);
                tx.setBalance(account.getBalance());
                tx.setStatus("FAIL");
                tx.setErrorMessage("Insufficient funds");
            } else {
                // apply transaction
                double newBalance = account.getBalance() + message.getAmount();
                account.setBalance(newBalance);
                accountRepository.save(account);

                tx.setAccountId(accountId);
                tx.setBalance(newBalance);
                tx.setStatus("OK");
                tx.setErrorMessage("NONE");
            }

            transactionRepository.save(tx);
            notifyWebhook(tx);
        } finally {
            lock.unlock();
        }
    }

    private void notifyWebhook(TransactionEntity tx) {
        if (webhookUrl == null || webhookUrl.isBlank()) {
            return;
        }
        try {
            WebhookNotification webhookNotification =
                new WebhookNotification(tx.getId(), tx.getStatus(), tx.getErrorMessage());

            webClient.post()
                .uri(webhookUrl)
                .bodyValue(webhookNotification)
                .exchangeToMono(response ->
                    response.bodyToMono(String.class)
                        .defaultIfEmpty("") // avoid null body
                        .map(body -> {
                            int statusCode = response.statusCode().value();
                            logger.info("Webhook URL: {}, response: status={}, body={}", webhookUrl, statusCode, body);
                            return body;
                        })
                )
                .subscribe();

        } catch (Exception e) {
            logger.error("Webhook notification failed", e);
        }
    }
}
