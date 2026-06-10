package com.condor.transactionsengine.persistence.repository;

import com.condor.transactionsengine.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomerId(Long customerId);
}
