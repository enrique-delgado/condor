package com.condor.transactionsmanager.persistence.repository;

import com.condor.transactionsmanager.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomerId(Long customerId);
}
