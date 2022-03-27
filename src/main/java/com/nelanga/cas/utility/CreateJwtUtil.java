package com.nelanga.cas.utility;

import com.nelanga.cas.commons.user.AppUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateJwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY_STRING;

    @Value("${jwt.issuer}")
    private String ISSUER;

    @Value("${jwt.lifetime}")
    private int JWT_LIFETIME; // hours

    @Value("${jwt.claims.roles}")
    private String CLAIM_ROLES;

    public String generateToken(AppUserDetails userDetails) {
        List<String> roleClaims = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return createToken(roleClaims, userDetails.getId().toString());
    }

    private String createToken(List<String> roles, String subject) {

        final SecretKey SECRET_KEY = HS256KeyUtil.secretKeyFrom(SECRET_KEY_STRING);

        return Jwts.builder()
                .setSubject(subject) // whom the token refers to {user_id/name}
                .claim(CLAIM_ROLES, String.join(",", roles))
                .setIssuer(ISSUER)
                .setIssuedAt(currentTimeInUnix())
                .setExpiration(expirationDateInUnix())
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
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
