package com.condor.transactionsmanager.service;

import com.condor.transactionsmanager.persistence.entity.CustomerEntity;
import com.condor.transactionsmanager.dto.report.AccountStatementDTO;
import com.condor.transactionsmanager.dto.report.CustomerStatementDTO;
import com.condor.transactionsmanager.mapper.TransactionReportMapper;
import com.condor.transactionsmanager.persistence.entity.AccountEntity;
import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import com.condor.transactionsmanager.persistence.repository.AccountRepository;
import com.condor.transactionsmanager.persistence.repository.CustomerRepository;
import com.condor.transactionsmanager.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionReportMapper transactionReportMapper;

    public StatementService(CustomerRepository customerRepository,
                            AccountRepository accountRepository,
                            TransactionRepository transactionRepository,
                            TransactionReportMapper transactionReportMapper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionReportMapper = transactionReportMapper;
    }

    public CustomerStatementDTO getStatement(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer.not.found"));

        List<AccountEntity> accounts = accountRepository.findByCustomerId(customerId);

        List<AccountStatementDTO> accountStatements = accounts.stream().map(account -> {
            List<TransactionEntity> transactions = transactionRepository
                    .findByAccountIdAndCreatedAtBetweenOrderByCreatedAtAsc(account.getId(), startDate, endDate);

            AccountStatementDTO dto = new AccountStatementDTO();
            dto.setAccountNumber(account.getId());
            dto.setType(account.getType());
            dto.setBalance(account.getBalance());
            dto.setStatus(account.getStatus());
            dto.setTransactions(transactions.stream()
                    .map(transactionReportMapper::toDto)
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

        CustomerStatementDTO statement = new CustomerStatementDTO();
        statement.setCustomer(customer.getFirstName() + " " + customer.getLastName());
        statement.setAccounts(accountStatements);

        return statement;
    }
}
