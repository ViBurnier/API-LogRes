package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestCreate;
import com.LRProduct.api.account.model.AccountResponseModel;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.utils.JwtUtil;
import com.LRProduct.api.utils.ServiceAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Data
@RestController
@RequestMapping("/register")
public class AccountController {

    ServiceAccount serviceAccount;
    JwtUtil jwtUtil;
    @PostMapping("/create")
    public ResponseEntity<?> register(@Valid @RequestBody AccountRequestCreate accountRequestModel){

        try {
            Account account = serviceAccount.createNewAccount(accountRequestModel);

            AccountResponseModel accountResponseModel = new AccountResponseModel(
                    account.getEmail(),
                    account.getName(),
                    account.getBirth()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Conta criada com successo", "201", accountResponseModel));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage(), "400"));
        }
    }
}
