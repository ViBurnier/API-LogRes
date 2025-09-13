package com.LRProduct.api.account.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestEdit {

    @Size(min = 1)
    private String name;

    @Email(message = "Email deve ter um formato válido")
    @Size(min = 10)
    private String email;
}
