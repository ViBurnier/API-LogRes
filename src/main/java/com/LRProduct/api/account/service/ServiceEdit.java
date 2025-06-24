package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestEdit;
import com.LRProduct.api.account.model.AccountResponseEdit;
import com.LRProduct.api.account.model.AccountResponseLogin;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.CookieService;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.image.PixelGrabber;
import java.util.Optional;
@Service
public class ServiceEdit {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookieService cookie;

    @Autowired
    AccountRepository accountRepository;

    public Account validateEditAccount(String jwtToken, Long userId, String getToken, AccountRequestEdit accountRequestEdit){

        //fazer um metodo para chamar.
        Optional<Account> email = accountRepository.findByEmail(accountRequestEdit.getEmail());
        if(email.isPresent()){
            throw new ApiException("Email não disponível.", "400", HttpStatus.BAD_REQUEST);
        }

        if(userId == null){
            throw new ApiException("ID nulo", "400", HttpStatus.BAD_REQUEST);
        }

        Optional<Account> user = accountRepository.findById(userId);

        Account account = user.get();

        //Verificar disponibilidade do email

        if(jwtToken.isBlank()){
            throw new ApiException("Token não válido", "401", HttpStatus.BAD_REQUEST);
        }

        if(getToken == null){
            throw new ApiException("Não era pra você estar aqui!", "400", HttpStatus.BAD_REQUEST);
        }

        if ( account == null || account.getStatus() != Account.Status.ON) {
            throw new ApiException("Usuário inválido", "401", HttpStatus.BAD_REQUEST);
        }

        return account;
    }



     public AccountResponseEdit editAccount(AccountRequestEdit accountRequestEdit, HttpServletRequest request){

        String getToken = cookie.getTokenFromRequest(request);

        String jwtToken = jwtUtil.extractTokenFromCookies(request);

        Long userID = jwtUtil.getUserId(jwtToken);



        Account account = validateEditAccount(jwtToken, userID, getToken, accountRequestEdit);

         account.setEmail(accountRequestEdit.getEmail());
         account.setName(accountRequestEdit.getName());

         account = accountRepository.save(account);

        return new AccountResponseEdit(
                account.getName(),
                account.getEmail()
        );
     }
}
