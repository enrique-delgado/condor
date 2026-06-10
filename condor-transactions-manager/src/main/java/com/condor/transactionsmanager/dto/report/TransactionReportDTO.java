package com.condor.transactionsmanager.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionReportDTO {
    private LocalDateTime date;
    private String status;
    private Double movement;
    private Double balance;
    private Double available;
}
