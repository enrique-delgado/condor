package com.condor.transactionsmanager.mapper;

import com.condor.commons.util.DateTimeUtils;
import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.event.TransactionInitiatedEventProto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionProtoMapper {

    default TransactionInitiatedEventProto.TransactionInitiatedEvent toProto(Transaction tx) {
        return TransactionInitiatedEventProto.TransactionInitiatedEvent.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setAccountId(tx.getAccountId())
                .setAmount(tx.getAmount())
                .setRequestedAt(DateTimeUtils.toEpochMillis(LocalDateTime.now()))
                .build();
    }

    default Transaction toService(TransactionInitiatedEventProto.TransactionInitiatedEvent event) {
        return Transaction.builder()
            .id(event.getId())
            .build();
    }
}
