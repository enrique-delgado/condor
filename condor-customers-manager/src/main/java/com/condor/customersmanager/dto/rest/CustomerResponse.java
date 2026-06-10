package com.condor.customersmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerResponse {

    @Schema(description = "Internal ID of the customer", example = "1")
    private Long id;

    @Schema(description = "Customer's first name", example = "Marianela")
    private String firstName;

    @Schema(description = "Customer's last name", example = "Perez")
    private String lastName;

    @Schema(description = "Identification number", example = "ID12345")
    private String identification;

    @Schema(description = "Customer's address", example = "Amazonas and NNUU")
    private String address;

    @Schema(description = "Contact phone number", example = "5678")
    private String phone;

    @Schema(description = "Active status of the customer", example = "true")
    private boolean active;
}
