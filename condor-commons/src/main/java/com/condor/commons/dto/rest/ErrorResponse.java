package com.condor.commons.dto.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error message summary", example = "Validation failed")
    private String message;

    @Schema(description = "Detailed field errors with messages")
    private Map<String, String> errors;
}
