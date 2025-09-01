package com.LRProduct.api.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@AllArgsConstructor
//Envia apenas os dados necessarios para exibicao
public class AccountResponsePhoto {
    private String photoFile;


}