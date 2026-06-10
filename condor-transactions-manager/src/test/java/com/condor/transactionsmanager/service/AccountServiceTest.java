package com.condor.transactionsmanager.service;

import com.condor.transactionsmanager.dto.service.Account;
import com.condor.transactionsmanager.exception.AccountNotFoundException;
import com.condor.transactionsmanager.persistence.entity.AccountEntity;
import com.condor.transactionsmanager.persistence.repository.AccountRepository;
import com.condor.transactionsmanager.mapper.AccountServiceMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private final AccountRepository repository = Mockito.mock(AccountRepository.class);
    private final AccountServiceMapper mapper = Mockito.mock(AccountServiceMapper.class);
    private final AccountService service = new AccountService(repository, mapper);

    @Test
    void testGetByIdSuccess() {
        AccountEntity entity = new AccountEntity();
        entity.setId(1L);
        entity.setCustomerId(1L);
        entity.setType("SAVINGS");
        entity.setBalance(500.0);
        entity.setStatus("ACTIVE");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toService(entity)).thenReturn(new Account());

        Account account = service.getById(1L);
        assertNotNull(account);
    }

    @Test
    void testGetByIdNotFound() {
        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> service.getById(99L));
    }
}
