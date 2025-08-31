package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestEdit;
import com.LRProduct.api.account.model.AccountResponseEdit;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ServiceEdit {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookieService cookie;

    @Autowired
    AccountRepository accountRepository;

    public void validateEditAccount(String getToken, AccountRequestEdit accountRequestEdit){

        Optional<Account> email = accountRepository.findByEmail(accountRequestEdit.getEmail());

        if(email.isPresent()){
            throw new ApiException("Email não disponível.", "400", HttpStatus.BAD_REQUEST);
        }

        if(getToken == null){
            throw new ApiException("Usuário deslogado", "400", HttpStatus.BAD_REQUEST);
        }

    }

    //se alterar somente um campo o outro fica null.
     public AccountResponseEdit editAccount(AccountRequestEdit accountRequestEdit, HttpServletRequest request){

        String getToken = cookie.getTokenFromRequest(request);

        validateEditAccount(getToken, accountRequestEdit);

         Account account = jwtUtil.getLoggedUser(request, accountRepository);

         if(accountRequestEdit.getEmail() != null){
            account.setEmail(accountRequestEdit.getEmail());
         }

         if(accountRequestEdit.getName() != null){
            account.setName(accountRequestEdit.getName());
         }

         account = accountRepository.save(account);

        return new AccountResponseEdit(
                account.getName(),
                account.getEmail()
        );
     }
}
