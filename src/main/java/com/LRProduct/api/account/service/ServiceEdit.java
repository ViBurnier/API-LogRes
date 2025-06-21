package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestEdit;
import com.LRProduct.api.account.model.AccountResponseEdit;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceEdit {
    @Autowired
    JwtUtil jwtUtil;
    //String getToken = cookie.getTokenFromRequest(request);

    @Autowired
    AccountRepository accountRepository;
     public AccountResponseEdit editAccount(AccountRequestEdit accountRequestEdit, HttpServletRequest request){
        String jwtToken = jwtUtil.extractTokenFromCookies(request);

        if(jwtToken.isBlank()){
            throw new ApiException("Token não válido", "401", HttpStatus.BAD_REQUEST);
        }

        Long userID = jwtUtil.getUserId(jwtToken);
        Optional<Account> user = accountRepository.findById(userID);

        Account account = user.get();

         if (account == null || account.getStatus() != Account.Status.ON) {
             throw new ApiException("Usuário inválido", "401", HttpStatus.BAD_REQUEST);
         }

         account.setEmail(accountRequestEdit.getEmail());
         account.setName(accountRequestEdit.getName());

         account = accountRepository.save(account);

        return new AccountResponseEdit(
                account.getName(),
                account.getEmail()
        );
     }
}
