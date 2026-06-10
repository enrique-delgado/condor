package com.condor.transactionsmanager.service;

import com.condor.transactionsmanager.dto.service.Account;
import com.condor.transactionsmanager.exception.AccountNotFoundException;
import com.condor.transactionsmanager.persistence.repository.AccountRepository;
import com.condor.transactionsmanager.mapper.AccountServiceMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;
    private final AccountServiceMapper mapper;

    public AccountService(AccountRepository repository, AccountServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Account create(Account account) {
        return mapper.toService(repository.save(mapper.toEntity(account)));
    }

    public Account getById(Long id) {
        return repository.findById(id)
                .map(mapper::toService)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public boolean deactivate(Long id) {

        if (repository.existsById(id)) {
            var entity = repository.findById(id).get();
            entity.setStatus("INACTIVE");
            repository.save(entity);
            return true;
        }
        return false;

    }
}
