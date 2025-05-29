package com.LRProduct.api.utils;


import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    @Getter
    private final String code;

    @Getter
    private HttpStatus httpStatus;

    public ApiException(String message, String code, HttpStatus httpStatus){
        super(message);
        this.code =  code;
        this.httpStatus = httpStatus;
    }

}
