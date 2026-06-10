package com.condor.transactionsmanager.mapper;

import com.condor.transactionsmanager.dto.report.TransactionReportDTO;
import com.condor.transactionsmanager.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionReportMapper {

    @Mapping(source = "createdAt", target = "date")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "amount", target = "movement")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "balance", target = "available")
    TransactionReportDTO toDto(TransactionEntity entity);
}
