package com.condor.transactionsmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Request DTO for creating or updating an account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

        @Schema(description = "Customer identifier", example = "1001")
        @NotNull(message = "{account.customerId.notnull}")
        private Long customerId;

        @Schema(description = "Account type", example = "SAVINGS")
        @NotBlank(message = "{account.type.notblank}")
        @Size(min = 3, max = 20, message = "{account.type.size}")
        private String type;

        @Schema(description = "Initial balance", example = "500.00")
        @NotNull(message = "{account.balance.notnull}")
        private Double balance;

        @Schema(description = "Account password", example = "securePass123")
        @NotBlank(message = "{account.password.notblank}")
        @Size(min = 5, max = 20, message = "{account.password.size}")
        private String password;

        @Schema(description = "Account status", example = "ACTIVE")
        @NotBlank(message = "{account.status.notblank}")
        @Size(min = 5, max = 20, message = "{account.status.size}")
        private String status;
}
