package com.LRProduct.api.utils;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuth {

    @Autowired
    AccountRepository accountRepository;
    JwtUtil jwtUtil;
    CookieService cookie;

    public void validateAccountLogin(AccountRequestLogin requestLogin, HttpServletRequest request, Optional<Account> opt){

        String getToken = cookie.getTokenFromRequest(request);

        jwtUtil.tokenValid(getToken);

        if(getToken != null){
            throw new IllegalArgumentException("Voce ja esta logado.");
        }

        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Email nao encontrado.");
        }

    }

    public Account loginAccount(AccountRequestLogin accountRequestLogin, HttpServletRequest request){


        String email = accountRequestLogin.getEmail();
        String password = accountRequestLogin.getPassword();
        Optional<Account> opt = accountRepository.findByEmail(email.trim().toLowerCase());
        validateAccountLogin(accountRequestLogin, request, opt);



    }
}
