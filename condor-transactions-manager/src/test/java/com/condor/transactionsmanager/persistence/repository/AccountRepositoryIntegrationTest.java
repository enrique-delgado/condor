package com.condor.transactionsmanager.persistence.repository;

import com.condor.transactionsmanager.RandomFactory;
import com.condor.transactionsmanager.persistence.entity.AccountEntity;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository repository;

    private final EasyRandom easyRandom = RandomFactory.getEasyRandom();

    @Test
    void testFindByCustomerId() {
        AccountEntity accountToSave = easyRandom.nextObject(AccountEntity.class);;
        final long customerId = 1;

        accountToSave.setCustomerId(customerId);
        accountToSave.setType("SAVINGS");
        repository.save(accountToSave);

        List<AccountEntity> accounts = repository.findByCustomerId(customerId);
        assertFalse(accounts.isEmpty());
        assertEquals("SAVINGS", accounts.get(0).getType());
    }
}
