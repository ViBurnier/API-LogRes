package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class LoginController {


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AccountRequestLogin accountRequestLogin, HttpServletRequest request){





    }
}
