package com.LRProduct.api.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountResponseLogin {
    private String email;
    private String password;
    private String token;
}
