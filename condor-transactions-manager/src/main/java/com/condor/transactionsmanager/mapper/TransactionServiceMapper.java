package com.condor.transactionsmanager.mapper;

import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionServiceMapper {
    TransactionEntity toEntity(Transaction transaction);
    Transaction toService(TransactionEntity entity);
}
