package com.condor.transactionsmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Request DTO for initiating a transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @Schema(description = "Account identifier", example = "2001")
    @NotNull(message = "{transaction.accountId.notnull}")
    private Long accountId;

    @Schema(description = "Transaction amount", example = "150.00")
    @NotNull(message = "{transaction.amount.notnull}")
    private Double amount;
}
