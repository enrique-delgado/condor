package com.condor.transactionsengine.persistence.repository;

import com.condor.transactionsengine.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findByAccountId(Long accountId);
}
