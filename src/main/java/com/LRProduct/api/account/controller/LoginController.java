package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.account.service.ServiceAuth;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    ServiceAuth serviceAuth;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AccountRequestLogin accountRequestLogin, HttpServletRequest request, HttpServletResponse response){

        try{
            AccountResponseLogin data = serviceAuth.loginAccount(accountRequestLogin, request, response);

            return ResponseEntity.ok(ApiResponse.success("Login realizado com sucesso.", "200", data));
        }catch (ApiException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }


    }
}
