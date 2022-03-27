package com.nelanga.cas.auth;

import com.nelanga.cas.commons.user.AppUserDetails;
import com.nelanga.cas.utility.CreateCookieUtil;
import com.nelanga.cas.utility.CreateJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private CreateJwtUtil jwtUtil;
    private CreateCookieUtil cookieUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, CreateJwtUtil jwtUtil, CreateCookieUtil cookieUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.cookieUtil = cookieUtil;
    }

    public ResponseEntity<Object> signInUser(SignInRequest request) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        AppUserDetails userDetails = (AppUserDetails) auth.getPrincipal();

        log.info("Generating token for user {} [{}]", userDetails.getId(), userDetails.getAuthorities().size());

        String jwt = jwtUtil.generateToken(userDetails);
        String cookie = cookieUtil.jwt(jwt).toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie)
                .build();
    }
}
