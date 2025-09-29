package com.LRProduct.api.account.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "account")
//Representa dados no BD
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ON;

    private String photo;

    @Enumerated(EnumType.STRING)
    private VerificationAccount verificationAccount = VerificationAccount.OFF;


    private LocalDateTime verificationCodeExpired;

    private int verificationCode;

    public enum Role{
        USER,
        ADMIN,
        OPERATOR
    }

    public enum Status{
        ON,
        OFF
    }

    public enum VerificationAccount{
        ON,
        OFF
    }
}
