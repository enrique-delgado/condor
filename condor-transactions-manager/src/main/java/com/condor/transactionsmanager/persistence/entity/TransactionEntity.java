package com.condor.transactionsmanager.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "error_message", nullable = false, length = 255)
    private String errorMessage;

    @Column(name = "status", nullable = false, length = 10)
    private String status;
}
