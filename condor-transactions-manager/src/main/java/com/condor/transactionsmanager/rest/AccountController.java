package com.condor.transactionsmanager.rest;

import com.condor.transactionsmanager.dto.rest.AccountRequest;
import com.condor.transactionsmanager.dto.rest.AccountResponse;
import com.condor.transactionsmanager.dto.service.Account;
import com.condor.transactionsmanager.mapper.AccountRestMapper;
import com.condor.transactionsmanager.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;
    private final AccountRestMapper mapper;

    public AccountController(AccountService service, AccountRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Crear cuenta", description = "Crea una nueva cuenta para un cliente")
    @ApiResponse(responseCode = "200", description = "Cuenta creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public AccountResponse create(@Valid @RequestBody AccountRequest request) {
        Account account = mapper.toService(request);
        return mapper.toResponse(service.create(account));
    }

    @Operation(summary = "Obtener cuenta por ID", description = "Devuelve una cuenta usando su identificador")
    @ApiResponse(responseCode = "200", description = "Cuenta encontrada")
    @ApiResponse(responseCode = "404", description = "Cuenta no existe")
    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable("id") Long id) {
        return mapper.toResponse(service.getById(id));
    }

    @Operation(summary = "Desactivar cuenta", description = "Desactiva una cuenta existente")
    @ApiResponse(responseCode = "200", description = "Cuenta desactivada")
    @ApiResponse(responseCode = "404", description = "Cuenta no existe")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        boolean deactivated = service.deactivate(id);
        if (deactivated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
