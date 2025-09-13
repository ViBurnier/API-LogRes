package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.DTOs.AccountRequestCreate;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceAccount {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;


    public void validateCreateNewAccount(AccountRequestCreate accountRequest, HttpServletRequest httpServletRequest){

        //não deixa criar conta logado.
        if(jwtUtil.getLoggedUser(httpServletRequest, accountRepository) != null){
            throw new ApiException("Você está logado, deslogue para criar uma conta.", "400", HttpStatus.BAD_REQUEST);
        }


        //verifica a disponibilidade do email.
        Optional<Account> email = accountRepository.findByEmail(accountRequest.getEmail());
        if(email.isPresent()){
            throw new ApiException("Email não disponível.", "400", HttpStatus.BAD_REQUEST);
        }

        if(!Objects.equals(accountRequest.getPassword(), accountRequest.getPasswordTwo())){
            throw new ApiException("As senhas não coincidem", "400", HttpStatus.BAD_REQUEST);
        }

        
        //obrigatorio um caracter especial, um numero e uma letra
        if(!accountRequest.getPassword().matches("(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|@])(?=.*[a-zA-Z])(?=.*[0-9]).+")){
            throw new ApiException("A senha não obedece o necessário.", "400", HttpStatus.BAD_REQUEST);
        }

    }

    public Account createNewAccount(AccountRequestCreate accountRequestModel, HttpServletRequest request){

        validateCreateNewAccount(accountRequestModel, request);

        Account account = new Account();
        account.setName(accountRequestModel.getName());
        account.setBirth(accountRequestModel.getBirth());
        account.setEmail(accountRequestModel.getEmail());
        account.setPassword(BCrypt.hashpw(accountRequestModel.getPassword(), BCrypt.gensalt()));

        account = accountRepository.save(account);

        return account;
    }


}
