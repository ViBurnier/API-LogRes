package com.LRProduct.api.utils;

import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.repository.AccountRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import static org.springframework.web.util.WebUtils.getCookie;

@Service
public class JwtUtil {
    private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P";
    private static final String ISSUER = "apiLogREs";

    public String generateToken(String subject, Map<String, Object> claims){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(subject)
                    .withClaim(claims)
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new JWTCreationException("erro ao gerar token.", exception);
        }
    }

    public String tokenValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }


        private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(48).toInstant();
    }

    public String extractTokenFromCookies(HttpServletRequest request){
        if(request.getCookies() != null){
            for( Cookie cookie : request.getCookies()){
                if("token".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public Long getUserId(String token) {
        try{

        }
    }

    public Account getLoggedUser(HttpServletRequest request, AccountRepository repo){
        String token = extractTokenFromCookies(request);

        tokenValid(token);

        if (token == null) {
            return null;
        }

        Long accountId = getUserId(token);

    }


}
