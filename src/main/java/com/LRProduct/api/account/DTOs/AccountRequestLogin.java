package com.LRProduct.api.account.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestLogin {


    @NotBlank(message = "Email é obrigatorio!")
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    private String password;
}
