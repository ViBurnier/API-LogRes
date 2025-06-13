package com.LRProduct.api.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseEdit {
    private String name;
    private String email;
}
