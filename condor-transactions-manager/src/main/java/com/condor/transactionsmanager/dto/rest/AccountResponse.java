package com.condor.transactionsmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Response DTO for account information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    @Schema(description = "Account identifier", example = "2001")
    private Long id;

    @Schema(description = "Customer identifier", example = "1001")
    private Long customerId;

    @Schema(description = "Account type", example = "SAVINGS")
    private String type;

    @Schema(description = "Current balance", example = "750.00")
    private Double balance;

    @Schema(description = "Account status", example = "ACTIVE")
    private String status;
}
