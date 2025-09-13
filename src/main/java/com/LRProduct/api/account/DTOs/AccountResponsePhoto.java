package com.LRProduct.api.account.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//Envia apenas os dados necessarios para exibicao
public class AccountResponsePhoto {
    private String photoFile;


}