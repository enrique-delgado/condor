package com.condor.transactionsmanager.dto.service;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {

    private String id;

    private Long accountId;

    private Double amount;

    private Double balance;

    private LocalDateTime requestedAt;

    private LocalDateTime createdAt;

    private String errorMessage;

    private String status;
}
