package com.condor.transactionsmanager.persistence.repository;

import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface
TransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findByAccountId(Long accountId);

    List<TransactionEntity> findByAccountIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long accountId, LocalDateTime start, LocalDateTime end);
}
