package com.LRProduct.api.utils;

import java.util.Map;

public class ApiException extends RuntimeException{

    private final Map<String, Object> body;

    public ApiException(Map<String, Object> body){
        this.body = body;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
