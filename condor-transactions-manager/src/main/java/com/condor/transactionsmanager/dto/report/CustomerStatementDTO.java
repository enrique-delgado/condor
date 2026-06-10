package com.condor.transactionsmanager.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CustomerStatementDTO {
    private String customer;
    private List<AccountStatementDTO> accounts;
}