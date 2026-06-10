package com.condor.transactionsmanager.rest;

import com.condor.transactionsmanager.dto.report.CustomerStatementDTO;
import com.condor.transactionsmanager.service.StatementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customers/{customerId}/accounts/statement")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @Operation(summary = "Get account statement for a customer within a date range")
    @GetMapping
    public CustomerStatementDTO getStatement(
            @PathVariable("customerId") Long customerId,
            @RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return statementService.getStatement(customerId, startDate, endDate);
    }
}
