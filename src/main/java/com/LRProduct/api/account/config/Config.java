package com.LRProduct.api.account.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
@Configuration
@Getter
public class Config implements WebMvcConfigurer{
    private final int cookieMaxAge = 48;
    private final int tokenMaxAge = 1;
    private final boolean httpsOn = false;
    private final List<String> supportedPhotoFormat = List.of("image/jpeg", "image/png");
    private final int maxPhotoSizeMb = 1230;
    private long maxFileSize = maxPhotoSizeMb * 1024 * 1024;
    private final String uploadDir = "./uploads/photo";
    private final String uploadUrl = "/api/account/photo";
}
