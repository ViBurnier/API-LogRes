package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.DTOs.AccountRequestLogin;
import com.LRProduct.api.account.DTOs.AccountResponseLogin;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.account.service.ServiceAuth;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {

    private final ServiceAuth serviceAuth;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody AccountRequestLogin accountRequestLogin, HttpServletRequest request, HttpServletResponse response){

        try{
            AccountResponseLogin data = serviceAuth.loginAccount(accountRequestLogin, request, response);

            return ResponseEntity.ok(ApiResponse.success("Login realizado com sucesso.", "200", data));
        }catch (ApiException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }


    }
}
