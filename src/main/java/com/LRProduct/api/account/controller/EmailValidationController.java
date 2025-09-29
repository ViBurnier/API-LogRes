package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.DTOs.AccountRequestEmailValidation;
import com.LRProduct.api.account.service.ServiceEmailValidation;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/account")
@RequiredArgsConstructor
@RestController
public class EmailValidationController {

            private final ServiceEmailValidation serviceEmailValidation;
    @PostMapping("/sendCode")
    public ResponseEntity<Map<String, Object>> sendCodeEmail(HttpServletRequest request){
        try{

            serviceEmailValidation.sendCodeEmail(request);

            return ResponseEntity.ok(ApiResponse.success("Código enviado para seu email com sucesso.", "200"));
        }
        catch (ApiException e){
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    };

    @PostMapping
    public ResponseEntity<Map<String, Object>> verifyCode(@Valid @RequestBody AccountRequestEmailValidation accountRequestEmailValidation, HttpServletRequest request){
        try {
            serviceEmailValidation.validationCode(request, accountRequestEmailValidation);

            return ResponseEntity.ok(ApiResponse.success("Conta verificada com sucesso.", "200"));
        }
        catch (ApiException e){
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    }
}
