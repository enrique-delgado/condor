package com.condor.customersmanager.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate birthDate;

    @Column(unique = true)
    private String identification;

    private String address;

    private String phone;

    private String password;

    private Boolean active;
}
