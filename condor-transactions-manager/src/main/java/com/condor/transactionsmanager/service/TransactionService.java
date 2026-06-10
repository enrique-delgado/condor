package com.condor.transactionsmanager.service;

import com.condor.commons.config.KafkaConstants;
import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.transactionsmanager.exception.TransactionNotFoundException;
import com.condor.transactionsmanager.integration.TransactionProducer;
import com.condor.transactionsmanager.mapper.TransactionProtoMapper;
import com.condor.transactionsmanager.mapper.TransactionServiceMapper;
import com.condor.transactionsmanager.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionServiceMapper mapper;
    private final TransactionProducer transactionProducer;

    public String create(Transaction tx) {
        String transactionId = transactionProducer.produce(tx);
        tx.setId(transactionId);
        log.info("Published {}", tx);

        return transactionId;
    }

    public Transaction getById(String id) {
        return mapper.toService(repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id)));
    }
}
