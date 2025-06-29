package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestLogin;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceAuth {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookieService cookie;

    public Account validateLoginAccount(HttpServletRequest request, Optional<Account> opt, String password){

        String getToken = cookie.getTokenFromRequest(request);

        Map<String, Object> error = new HashMap<>();

        if(getToken != null){
            throw new ApiException("Você já esta logado.", "400", HttpStatus.BAD_REQUEST);
        }

        if (opt.isEmpty()) {
            throw new ApiException("Conta não encontrada.", "404", HttpStatus.NOT_FOUND);
        }

        Account account = opt.get();

        if (!BCrypt.checkpw(password, account.getPassword())) {
            throw new ApiException("Senha incorreta.", "401", HttpStatus.UNAUTHORIZED);
        }

        if(!account.getStatus().name().equals("ON")){
            throw new ApiException("Conta desativada.", "401", HttpStatus.FORBIDDEN);
        }

        return account;
    }

    public AccountResponseLogin  loginAccount(AccountRequestLogin accountRequestLogin, HttpServletRequest request, HttpServletResponse response){

        String email = accountRequestLogin.getEmail();

        String password = accountRequestLogin.getPassword();

        Optional<Account> optFind = accountRepository.findByEmail(email.trim().toLowerCase());

        Account account = validateLoginAccount(request, optFind, password);

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
