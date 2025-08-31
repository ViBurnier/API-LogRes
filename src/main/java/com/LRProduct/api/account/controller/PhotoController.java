package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.AccountRequestPhoto;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.model.AccountResponsePhoto;
import com.LRProduct.api.account.service.ServicePhoto;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/account")
public class PhotoController {

    @Autowired
    ServicePhoto servicePhoto;

    @PostMapping(value = "/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> photo(
            @RequestParam("photo") MultipartFile photoFile,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        try {
            AccountResponsePhoto data = servicePhoto.photoAccount();

            return ResponseEntity.ok(ApiResponse.success("Foto atualizada com sucesso.", "201"));
        }
        catch (ApiException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    };
}
