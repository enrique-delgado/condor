package com.condor.transactionsengine.integration;

import com.condor.commons.config.KafkaConstants;
import com.condor.event.TransactionInitiatedEventProto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionInitiatedConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TransactionInitiatedConsumer.class);

    private final TransactionInitiatedProcessor transactionInitiatedProcessor;

    @KafkaListener(topics = KafkaConstants.TRANSACTIONS_INITIATED_TOPIC)
    public void consume(TransactionInitiatedEventProto.TransactionInitiatedEvent message) {
        try {
            logger.info("Message: {}", message);
            transactionInitiatedProcessor.process(message);
        } catch (Exception e) {
            logger.error("Processing Transfer", e);
        }
    }
}
