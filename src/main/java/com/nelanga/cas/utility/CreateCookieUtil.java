package com.nelanga.cas.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CreateCookieUtil {

    @Value("${jwt.tokenName}")
    private String jwtCookie;

    @Value("${domain}")
    private String domain;

    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;

    public ResponseCookie jwt(String token) {
        return ResponseCookie.from(jwtCookie, token)
                .httpOnly(true)
                .secure(false) // TODO: Set to true in production
                .maxAge(getCookieLifetime())
                .domain(domain)
                .path("/")
                .build();
    }

    private long getCookieLifetime() {
        return this.jwtLifetime * 60 * 60;
    }
}
