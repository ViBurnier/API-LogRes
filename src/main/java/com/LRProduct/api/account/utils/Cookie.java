package com.LRProduct.api.account.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Cookie {

    private final ObjectMapper objectMapper;

    private static final String TOKEN_COOKIE = "token";
    private static final String USER_COOKIE = "userdata";
}
