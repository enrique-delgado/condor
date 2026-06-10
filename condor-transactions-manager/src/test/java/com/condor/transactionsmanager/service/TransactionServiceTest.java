package com.condor.transactionsmanager.service;

import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.transactionsmanager.exception.TransactionNotFoundException;
import com.condor.transactionsmanager.integration.TransactionProducer;
import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import com.condor.transactionsmanager.persistence.repository.TransactionRepository;
import com.condor.transactionsmanager.mapper.TransactionServiceMapper;
import com.condor.transactionsmanager.mapper.TransactionProtoMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private TransactionServiceMapper mapper;

    @Mock
    private TransactionProducer producer;

    @InjectMocks
    private TransactionService service;

    @Test
    void testGetByIdSuccess() {
        TransactionEntity entity = new TransactionEntity();
        entity.setId("tx-1001");
        entity.setAccountId(1L);
        entity.setAmount(150.0);

        Mockito.when(repository.findById("tx-1001")).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toService(entity)).thenReturn(new Transaction());

        Transaction tx = service.getById("tx-1001");
        assertNotNull(tx);
    }

    @Test
    void testGetByIdNotFound() {
        Mockito.when(repository.findById("tx-9999")).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> service.getById("tx-9999"));
    }
}
