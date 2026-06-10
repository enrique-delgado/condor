package com.condor.customersmanager.dto.service;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean active;
}
