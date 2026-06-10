package com.condor.transactionsengine.service.mapper;

import com.condor.event.TransactionInitiatedEventProto;
import com.condor.transactionsengine.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersistentTransactionProtoMapper {

    default TransactionEntity toEntity(TransactionInitiatedEventProto.TransactionInitiatedEvent event) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(event.getId());
        entity.setAccountId(event.getAccountId());
        entity.setAmount(event.getAmount());
//        entity.setBalance(event.getBalance());
//        entity.setRequestedAt(java.time.LocalDateTime.parse(event.getRequestedAt()));
//        entity.setCreatedAt(java.time.LocalDateTime.parse(event.getCreatedAt()));
//        entity.setStatus(event.getStatus());
//        entity.setErrorMessage(event.getErrorMessage());
        return entity;
    }
}
