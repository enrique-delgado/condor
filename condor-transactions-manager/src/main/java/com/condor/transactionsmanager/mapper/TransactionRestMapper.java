package com.condor.transactionsmanager.mapper;

import com.condor.transactionsmanager.dto.rest.TransactionRequest;
import com.condor.transactionsmanager.dto.rest.TransactionResponse;
import com.condor.transactionsmanager.dto.service.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionRestMapper {
    Transaction toService(TransactionRequest request);
    TransactionResponse toResponse(Transaction transaction);
}

