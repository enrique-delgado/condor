package com.condor.transactionsmanager.persistence.repository;

import com.condor.transactionsmanager.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByIdentification(String identification);
}
