package com.condor.transactionsmanager.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "status", nullable = false, length = 20)
    private String status;
}
