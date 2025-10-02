package com.LRProduct.api.account.service;

import com.LRProduct.api.account.DTOs.AccountRequestEmailValidation;
import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.serviceglobal.ServiceEmail;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceEmailValidation {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ServiceEmail serviceMail;

    private void generatedCode(Account account){
        int code = (int)(Math.random() * 900000) + 100000;

        LocalDateTime timeCurrent = LocalDateTime.now();

        LocalDateTime timeExpired = timeCurrent.plusMinutes(5);



        account.setVerificationCode(code);
        account.setVerificationCodeExpired(timeExpired);
        accountRepository.save(account);
    }

//vou precisar pegar o email do campo de criação de usuário
//para usar nesse método. Verificar se: existe algum jeito de validar a existência do email.
//esse método vai ser chama por outro na criação de usuário, antes do user ser salvo no BD.
    public void sendCodeEmail(HttpServletRequest request) {

        if(jwtUtil.getLoggedUser(request, accountRepository) == null){
            throw new ApiException("Você precisa estar logado.", "401", HttpStatus.UNAUTHORIZED);
        }

        Account account = jwtUtil.getLoggedUser(request, accountRepository);

        generatedCode(account);

        serviceMail.sendEmail(account.getEmail(), "Verification code", String.valueOf(account.getVerificationCode()));
    }

    public void validationCode(HttpServletRequest request, AccountRequestEmailValidation accountRequestEmailValidation){
        if(jwtUtil.getLoggedUser(request, accountRepository) == null){
            throw new ApiException("Você precisa estar logado.", "401", HttpStatus.UNAUTHORIZED);
        }

        Account account = jwtUtil.getLoggedUser(request, accountRepository);


    }


}
