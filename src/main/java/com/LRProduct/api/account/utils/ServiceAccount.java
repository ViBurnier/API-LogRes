package com.LRProduct.api.account.utils;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestModel;
import com.LRProduct.api.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ServiceAccount {

    @Autowired
    AccountRepository accountRepository;

    public void validateAccountRequest(AccountRequestModel request){
        if(!Objects.equals(request.getPassword(), request.getPasswordTwo())){
            throw new IllegalArgumentException("As senhas não coincidem");
        }
    }

    public Account createNewAccount(AccountRequestModel accountRequestModel){

        validateAccountRequest(accountRequestModel);

        Account account = new Account();
        account.setName(accountRequestModel.getName());
        account.setBirth(accountRequestModel.getBirth());
        account.setEmail(accountRequestModel.getEmail());
        account.setPassword(BCrypt.hashpw(accountRequestModel.getPassword(), BCrypt.gensalt()));

        account = accountRepository.save(account);

        return account;
    }

}
