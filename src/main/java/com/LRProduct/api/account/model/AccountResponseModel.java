package com.LRProduct.api.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//Envia apenas os dados necessarios para exibicao
public class AccountResponseModel {
    private String email;
    private String name;
    private LocalDate birth;
}
