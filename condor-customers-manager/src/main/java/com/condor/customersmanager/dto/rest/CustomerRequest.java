package com.condor.customersmanager.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    @Schema(description = "Customer's first name", example = "Marianela")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    @Schema(description = "Customer's last name", example = "Perez")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "M|F", message = "Gender must be M or F")
    @Schema(description = "Customer's gender", example = "F")
    private String gender;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date format must be YYYY-MM-DD")
    @Schema(description = "Birth date in format YYYY-MM-DD", example = "1995-05-20")
    private String birthDate;

    @NotBlank
    @Size(min = 5, max = 20)
    @Schema(description = "Identification number", example = "ID12345")
    private String identification;

    @NotBlank
    @Schema(description = "Customer's address", example = "Amazonas and NNUU")
    private String address;

    @NotBlank
    @Pattern(regexp = "\\d{4,10}", message = "Phone must be numeric with 4 to 10 digits")
    @Schema(description = "Contact phone number", example = "5678")
    private String phone;

    @NotBlank
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Schema(description = "Encrypted password", example = "password123")
    private String password;

    @Schema(description = "Active status of the customer", example = "true")
    private boolean active;
}
