package com.nelanga.cas.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private final static KeyPair JWT_KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);

    private final static int JWT_EXPIRATION = 1000 * 24 * 60 * 60; // 24h

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject) // whom the token refers to {user_id/name}
                .setIssuedAt(currentTimeInUnix())
                .setExpiration(expirationDateInUnix())
                .signWith(JWT_KEY_PAIR.getPrivate())
                .compact();
    }

    private Date currentTimeInUnix() {
        return new Date(System.currentTimeMillis());
    }

    private Date expirationDateInUnix() {
        return new Date(System.currentTimeMillis() + JWT_EXPIRATION);
    }
}
