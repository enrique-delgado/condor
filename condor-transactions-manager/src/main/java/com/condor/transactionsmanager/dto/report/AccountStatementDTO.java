package com.condor.transactionsmanager.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountStatementDTO {
    private Long accountNumber;
    private String type;
    private Double balance;
    private String status;
    private List<TransactionReportDTO> transactions;
}
