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
public class AccountRequestEdit {

    @NotBlank(message = "Nome é obrigatorio")
    private String name;

    @Email(message = "Email deve ter um formato válido")
    @NotBlank(message = "Email é obrigatorio!")
    @Size(min = 10)
    private String email;
}
