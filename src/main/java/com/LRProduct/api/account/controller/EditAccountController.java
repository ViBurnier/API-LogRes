package com.LRProduct.api.account.controller;

import com.LRProduct.api.account.model.AccountRequestEdit;
import com.LRProduct.api.account.model.AccountResponseEdit;
import com.LRProduct.api.account.service.ServiceEdit;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class EditAccountController {

    @Autowired
    ServiceEdit serviceEdit;

    @PutMapping("/edit")
    public ResponseEntity<?> editController(@Valid @RequestBody AccountRequestEdit accountRequestEdit, HttpServletRequest request){
        try{
            AccountResponseEdit data = serviceEdit.editAccount(accountRequestEdit, request);

            return ResponseEntity.ok(ApiResponse.success("Conta editada com sucesso.", "200", data));
        }catch (ApiException e){
            return ResponseEntity.status(e.getHttpStatus()).body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    }
}
