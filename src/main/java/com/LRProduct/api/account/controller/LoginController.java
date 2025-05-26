package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.account.utils.JwtUtil;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class LoginController {
    public final JwtUtil jwtUtil;
    public final AccountRepository accountRepository;

}
