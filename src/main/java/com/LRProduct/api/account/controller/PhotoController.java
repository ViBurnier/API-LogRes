package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.DTOs.AccountRequestPhoto;
import com.LRProduct.api.account.DTOs.AccountResponsePhoto;
import com.LRProduct.api.account.service.ServicePhoto;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/account")
@CrossOrigin
public class PhotoController {


    ServicePhoto servicePhoto;

    @PostMapping(value = "/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> photo(AccountRequestPhoto accountRequestPhoto, HttpServletRequest request){
        try {
            AccountResponsePhoto data = servicePhoto.photoAccount(accountRequestPhoto, request );

            return ResponseEntity.ok(ApiResponse.success("Foto atualizada com sucesso.", "201", data));
        }
        catch (ApiException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage(), e.getCode()));
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
