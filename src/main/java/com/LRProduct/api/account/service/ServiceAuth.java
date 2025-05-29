package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuth {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookieService cookie;

    public Account validateAccountLogin(HttpServletRequest request, Optional<Account> opt, String password){

        String getToken = cookie.getTokenFromRequest(request);

        if(getToken != null){
            jwtUtil.tokenValid(getToken);
            throw new IllegalArgumentException("Voce ja esta logado.");
        }

        if (opt.isEmpty()) {
            throw new IllegalArgumentException("Conta nao encontrada.");
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

    public AccountResponseLogin  loginAccount(AccountRequestLogin accountRequestLogin, HttpServletRequest request, HttpServletResponse response){

        String email = accountRequestLogin.getEmail();

        String password = accountRequestLogin.getPassword();

        Optional<Account> opt = accountRepository.findByEmail(email.trim().toLowerCase());

        Account account = validateAccountLogin(request, opt, password);

        String token = jwtUtil.generateToken(email, account.getId());

        cookie.cookieToken(token, response);
        cookie.cookieUser(account, response);

        return new AccountResponseLogin(
                account.getEmail(),
                account.getPassword(),
                token
        );
    }
}
