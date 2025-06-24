package com.LRProduct.api.utils;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestCreate;
import com.LRProduct.api.account.model.AccountRequestEdit;
import com.LRProduct.api.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class FindByEmail {

    @Autowired
    AccountRepository accountRepository;
    public void findEmail(AccountRequestCreate accountRequest) {
        Optional<Account> email = accountRepository.findByEmail(accountRequest.getEmail());
        if (email.isPresent()) {
            throw new ApiException("Email não disponível.", "400", HttpStatus.BAD_REQUEST);
        }
    }
}
