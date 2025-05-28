package com.LRProduct.api.utils;

import com.LRProduct.api.account.config.Config;
import com.LRProduct.api.account.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class CookieService {

    private final Config config;

    //Cookie do JWT
    public void cookieToken(String token ,HttpServletResponse response){
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(config.getCookieMaxAge() * 3600);
        cookie.setSecure(config.isHttpsOn());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void cookieUser(Account account, HttpServletResponse response){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("id", account.getId());
            data.put("name", account.getName());
            data.put("role", account.getRole());

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            String encoded = URLEncoder.encode(json, StandardCharsets.UTF_8);

            Cookie cookie = new Cookie("userData", encoded);
            cookie.setHttpOnly(false);
            cookie.setMaxAge(config.getCookieMaxAge() * 3600);
            cookie.setSecure(config.isHttpsOn());
            cookie.setPath("/");

            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCookie(String name, HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from(name, "")
                .sameSite("Lax")
                .maxAge(0)
                .httpOnly("token".equals(name))
                .path("/")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
