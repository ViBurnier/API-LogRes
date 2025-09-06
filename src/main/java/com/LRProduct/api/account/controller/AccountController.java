package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestCreate;
import com.LRProduct.api.account.model.AccountResponseModel;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.account.service.ServiceAccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {
    @Autowired
    ServiceAccount serviceAccount;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccountRequestCreate accountRequestCreate, HttpServletRequest request){

        try {
            Account account = serviceAccount.createNewAccount(accountRequestCreate, request);

            AccountResponseModel accountResponseModel = new AccountResponseModel(
                    account.getEmail(),
                    account.getName(),
                    account.getBirth()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Conta criada com successo", "201", accountResponseModel));
        } catch (ApiException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    }
}
