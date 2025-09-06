package com.LRProduct.api.account.controller;

import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.ApiResponse;
import com.LRProduct.api.utils.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class LogoutController {
    @Autowired
    CookieService cookieService;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        try{
            cookieService.removeCookie("token", response);
            cookieService.removeCookie("userData", response);
            return ResponseEntity.ok(ApiResponse.success("Logout realizado com sucesso.", "200"));
        } catch (ApiException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage(), e.getCode()));
        }
    }
}
