package com.condor.transactionsmanager.integration;

import com.condor.commons.config.KafkaConstants;
import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.transactionsmanager.mapper.TransactionProtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {
    private final TransactionProtoMapper protoMapper;
    private final KafkaTemplate<String, TransactionInitiatedEventProto.TransactionInitiatedEvent> kafkaTemplate;

    public String produce(Transaction tx) {
        // Convert to Protobuf event
        TransactionInitiatedEventProto.TransactionInitiatedEvent event = protoMapper.toProto(tx);

        // Publish to Kafka
        kafkaTemplate.send(KafkaConstants.TRANSACTIONS_INITIATED_TOPIC, event);

        return event.getId();
    }
}
