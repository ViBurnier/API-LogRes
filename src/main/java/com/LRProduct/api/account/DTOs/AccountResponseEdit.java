package com.LRProduct.api.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseEdit {
    private String name;
    private String email;
}
