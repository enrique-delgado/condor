package com.condor.transactionsmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Response DTO for transaction information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    @Schema(description = "Transaction identifier", example = "tx-12345")
    private String id;

    @Schema(description = "Error message", example = "Insufficient funds")
    private String errorMessage;

    @Schema(description = "Status", example = "OK")
    private String status;
}
