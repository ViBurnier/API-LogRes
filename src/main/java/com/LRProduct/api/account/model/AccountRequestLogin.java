package com.LRProduct.api.account.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
