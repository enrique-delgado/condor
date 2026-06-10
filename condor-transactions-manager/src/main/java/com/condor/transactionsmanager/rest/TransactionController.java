package com.condor.transactionsmanager.rest;

import com.condor.transactionsmanager.dto.rest.TransactionRequest;
import com.condor.transactionsmanager.dto.rest.TransactionResponse;
import com.condor.transactionsmanager.dto.service.Transaction;
import com.condor.transactionsmanager.mapper.TransactionRestMapper;
import com.condor.transactionsmanager.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;
    private final TransactionRestMapper mapper;

    public TransactionController(TransactionService service, TransactionRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Crear transacción", description = "Crea una nueva transacción para una cuenta")
    @ApiResponse(responseCode = "200", description = "Transacción creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public TransactionResponse create(@Valid @RequestBody TransactionRequest request) {
        Transaction tx = mapper.toService(request);
        String transactionId = service.create(tx);

        tx.setId(transactionId);

        return mapper.toResponse(tx);
    }

    @Operation(summary = "Obtener transacción por ID", description = "Devuelve una transacción usando su identificador")
    @ApiResponse(responseCode = "200", description = "Transacción encontrada")
    @ApiResponse(responseCode = "404", description = "Transacción no existe")
    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable("id") String id) {
        return mapper.toResponse(service.getById(id));
    }
}
