package com.condor.transactionsmanager.persistence.repository;

import com.condor.transactionsmanager.RandomFactory;
import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryIntegrationTest {

    @Autowired
    private com.condor.transactionsmanager.persistence.repository.TransactionRepository repository;

    private final EasyRandom easyRandom = RandomFactory.getEasyRandom();

    @Test
    void testFindByAccountId() {
        TransactionEntity transactionToSave = easyRandom.nextObject(TransactionEntity.class);;
        final long accountId = 1;

        transactionToSave.setAccountId(accountId);
        transactionToSave.setStatus("SUCCESS");
        repository.save(transactionToSave);

        List<TransactionEntity> transactions = repository.findByAccountId(1L);
        assertFalse(transactions.isEmpty());
        assertEquals("SUCCESS", transactions.get(0).getStatus());
    }
}
