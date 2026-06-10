package com.condor.transactionsmanager.dto.service;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;

    private Long customerId;

    private String type;

    private Double balance;

    private String password;

    private String status;
}
