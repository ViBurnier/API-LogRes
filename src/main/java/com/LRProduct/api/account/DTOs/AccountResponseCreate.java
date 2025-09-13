package com.LRProduct.api.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//Envia apenas os dados necessarios para exibicao
public class AccountResponseCreate {
    private String email;
    private String name;
    private LocalDate birth;
}
