package com.LRProduct.api.utils;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAuth {

    @Autowired
    AccountRepository accountRepository;
    JwtUtil jwtUtil;
    CookieService cookie;

    public Account validateAccountLogin(HttpServletRequest request, Optional<Account> opt, String password){

        String getToken = cookie.getTokenFromRequest(request);

        if(getToken != null){
            jwtUtil.tokenValid(getToken);
            throw new IllegalArgumentException("Voce ja esta logado.");
        }

        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Conta nao encontrado.");
        }

        Account account = opt.get();

        if (!BCrypt.checkpw(password, account.getPassword())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        if(!account.getStatus().name().equals("ON")){
            throw new IllegalArgumentException("Conta desativada.");
        }

        return account;
    }

    public void loginAccount(AccountRequestLogin accountRequestLogin, HttpServletRequest request, HttpServletResponse response){

        String email = accountRequestLogin.getEmail();

        String password = accountRequestLogin.getPassword();

        Optional<Account> opt = accountRepository.findByEmail(email.trim().toLowerCase());

        Account account = validateAccountLogin(request, opt, password);

        String token = jwtUtil.generateToken(email, account.getId());

        cookie.cookieToken(token, response);
        cookie.cookieUser(account, response);

    }
}
