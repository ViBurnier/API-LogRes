package com.LRProduct.api.account.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
@Getter
public class Config implements WebMvcConfigurer{
    private final int cookieMaxAge = 48;
    private final int tokenMaxAge = 48;
    private final boolean httpsOn = false;

}
