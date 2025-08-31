package com.LRProduct.api.account.model;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestPhoto {


    private MultipartFile photoFile;

}
