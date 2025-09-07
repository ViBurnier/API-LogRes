package com.LRProduct.api.utils;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

    @Getter
     final String message;
    @Getter

    @Getter

    public ApiException(String message, String code, HttpStatus httpStatus){
        this.code =  code;
        this.httpStatus = httpStatus;
    }

}
