package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.DTOs.AccountRequestVerifyAccount;
import com.LRProduct.api.account.service.OTPService;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ControllerVerificationAccount {
            private final OTPService otpService;
    @PostMapping("/sendCode")
    public ResponseEntity<Map<String, Object>> sendCodeEmail(HttpServletRequest request){
        try{
            otpService.sendCodeEmail(request);

            return ResponseEntity.ok(ApiResponse.success("Código enviado para seu email com sucesso.", "200"));
        }
        catch (ApiException e){
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    };

    @PostMapping
    public ResponseEntity<Map<String, Object>> verifyCode(@Valid @RequestBody  AccountRequestVerifyAccount accountRequestVerifyAccount, HttpServletRequest request){
        try {
            otpService.verificationValidateAccount(request, accountRequestVerifyAccount);
            return ResponseEntity.ok(ApiResponse.success("Conta verificada com sucesso.", "200"));
        }
        catch (ApiException e){
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    }


}
