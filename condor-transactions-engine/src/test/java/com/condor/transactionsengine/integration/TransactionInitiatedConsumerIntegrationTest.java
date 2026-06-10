package com.condor.transactionsengine.integration;

import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsengine.persistence.repository.TransactionRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "transactions-topic" })
@Disabled
class TransactionInitiatedConsumerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, TransactionInitiatedEventProto.TransactionInitiatedEvent> kafkaTemplate;

    @Autowired
    private TransactionRepository repository;

    @Test
    void testConsumeTransactionEvent() throws Exception {
        // Crear evento Protobuf
        TransactionInitiatedEventProto.TransactionInitiatedEvent event =
                TransactionInitiatedEventProto.TransactionInitiatedEvent.newBuilder()
                        .setId("tx-test-1")
                        .setAccountId(1L)
                        .setAmount(100.0)
//                        .setBalance(600.0)
//                        .setRequestedAt(LocalDateTime.now().toString())
//                        .setCreatedAt(LocalDateTime.now().toString())
//                        .setStatus("SUCCESS")
//                        .setErrorMessage("")
                        .build();

        // Publicar en Kafka
        kafkaTemplate.send("transactions-topic", event);

        // Esperar un poco para que el consumidor procese
        Thread.sleep(2000);

        // Validar persistencia
        var entity = repository.findById("tx-test-1");
        assertTrue(entity.isPresent());
        assertEquals(100.0, entity.get().getAmount());
        assertEquals("SUCCESS", entity.get().getStatus());
    }
}
