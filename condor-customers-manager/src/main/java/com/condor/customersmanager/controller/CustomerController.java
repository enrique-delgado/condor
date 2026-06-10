package com.condor.customersmanager.controller;

import com.condor.customersmanager.dto.rest.CustomerRequest;
import com.condor.customersmanager.dto.rest.CustomerResponse;
import com.condor.commons.dto.rest.ErrorResponse;
import com.condor.customersmanager.dto.service.Customer;
import com.condor.customersmanager.mapper.CustomerRestMapper;
import com.condor.customersmanager.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Customer management operations")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRestMapper customerRestMapper;

    @Operation(summary = "Get all customers",
               description = "Returns the complete list of registered customers")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Unexpected server error",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> responses = customers.stream()
                .map(customerRestMapper::toResponse)
                .toList();
        return responses;
    }

    @Operation(summary = "Get customer by ID",
               description = "Returns a customer using its internal identifier")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected server error",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        return customerRestMapper.toResponse(customer);
    }

    @Operation(summary = "Create new customer",
               description = "Registers a new customer in the system")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Validation error - invalid input data",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected server error",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {
        Customer customer = customerRestMapper.toDomain(request);
        Customer saved = customerService.create(customer);
        return customerRestMapper.toResponse(saved);
    }

    @Operation(summary = "Update existing customer",
               description = "Updates customer data by its ID")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "400", description = "Validation error - invalid input data",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Customer not found",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected server error",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable("id") Long id,
        @Valid @RequestBody CustomerRequest request) {
        Customer existing = customerService.findById(id);
        customerRestMapper.updateDomainFromRequest(request, existing);
        Customer saved = customerService.update(id, existing);
        return customerRestMapper.toResponse(saved);
    }

    @Operation(summary = "Delete customer",
               description = "Deletes a customer by its ID")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Unexpected server error",
                 content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        boolean deleted = customerService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
