package com.nelanga.cas.commons.support;

import com.nelanga.cas.exception.types.AuthFailedException;
import com.nelanga.cas.exception.types.CasServiceException;
import com.nelanga.cas.commons.user.AppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class ValidateJwtUtil {

    private String PUBLIC_KEY;
    private String CLAIM_ROLES;

    public AppUserDetails parseToken(String jwt) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(PUBLIC_KEY)
                    .build()
                    .parseClaimsJws(jwt);
            String subject = claimsJws.getBody().getSubject();
            String[] roles = claimsJws.getBody()
                    .get(CLAIM_ROLES, String.class)
                    .split(",");

            long userId = Long.parseLong(subject);
            Collection<GrantedAuthority> authorities = Arrays.stream(roles)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new AppUserDetails(userId, "", "", "****", authorities);
        } catch (JwtException exception) {
            throw new AuthFailedException();
        } catch (NumberFormatException exception) {
            log.error("Malformed Jwt body: {}", exception.getMessage());
            throw new CasServiceException();
        }
    }

}
