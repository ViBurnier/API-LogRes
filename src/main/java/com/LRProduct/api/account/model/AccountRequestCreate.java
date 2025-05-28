package com.LRProduct.api.account.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Valida dados de entrada da API
public class AccountRequestCreate {

    @Email(message = "Email deve ter um formato válido")
    @NotBlank(message = "Email é obrigatorio!")
    @Size(min = 10)
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "Rescreva a senha para continuar;")
    private String passwordTwo;

    @NotBlank(message = "Nome é obrigatorio")
    private String name;

    @Past
    private LocalDate birth;
}
