package com.nelanga.cas.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateJwtUtil {

    private final static KeyPair JWT_KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);
    @Value("${jwt.keys.private}")
    private String privateKey;

    @Value("${jwt.lifetime}")
    private int JWT_LIFETIME; // hours

    @Value("${jwt.claims.roles}")
    private String CLAIM_ROLES;

    public String generateToken(UserDetails userDetails) {
        List<String> roleClaims = userDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());
        return createToken(roleClaims, userDetails.getUsername());
    }

    private String createToken(List<String> roles, String subject) {
        return Jwts.builder()
                .setSubject(subject) // whom the token refers to {user_id/name}
                .claim(CLAIM_ROLES, String.join(",", roles))
                .setIssuedAt(currentTimeInUnix())
                .setExpiration(expirationDateInUnix())
//                TODO: Use our own private key from the config
                .signWith(JWT_KEY_PAIR.getPrivate())
                .compact();
    }

    /*
    * In milliseconds
    * */
    private Integer getJwtExpiration() {
        return 1000 * this.JWT_LIFETIME * 60 * 60;
    }

    private Date currentTimeInUnix() {
        return new Date(System.currentTimeMillis());
    }

    private Date expirationDateInUnix() {
        return new Date(System.currentTimeMillis() + getJwtExpiration());
    }
}
