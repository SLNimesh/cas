package com.nelanga.cas.config;

import com.nelanga.cas.commons.security.JwtAuthEntryPoint;
import com.nelanga.cas.commons.security.JwtFilter;
import com.nelanga.cas.commons.support.ReadCookieUtil;
import com.nelanga.cas.commons.support.ValidateJwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaAuditing
public class ServiceConfig {

    @Value("${jwt.tokenName}")
    private String JWT_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET_KEY_STRING;

    @Value("${jwt.claims.roles}")
    private String CLAIM_ROLES;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthEntryPoint authEntryPoint() {
        return new JwtAuthEntryPoint();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(readCookieUtil(), validateJwtUtil());
    }

    @Bean
    public ReadCookieUtil readCookieUtil() {
        return new ReadCookieUtil(JWT_COOKIE);
    }

    @Bean
    public ValidateJwtUtil validateJwtUtil() {
        return new ValidateJwtUtil(SECRET_KEY_STRING, CLAIM_ROLES);
    }
}
