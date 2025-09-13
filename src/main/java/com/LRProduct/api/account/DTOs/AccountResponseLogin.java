package com.LRProduct.api.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseLogin {
    private String email;
    private String token;
}
