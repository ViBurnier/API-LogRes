package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.model.AccountResponseModel;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import com.LRProduct.api.utils.ServiceAuth;
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

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
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage(), "400"));
        }


    }
}
