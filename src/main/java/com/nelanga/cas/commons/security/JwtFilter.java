package com.nelanga.cas.commons.security;

import com.nelanga.cas.commons.support.ReadCookieUtil;
import com.nelanga.cas.commons.support.ValidateJwtUtil;
import com.nelanga.cas.commons.user.AppUserDetails;
import com.nelanga.cas.exception.types.CasServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private ReadCookieUtil cookieUtil;
    private ValidateJwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<String> jwt = cookieUtil.getJwtCookie(request);
        if (jwt.isPresent()) {
            try {
                AppUserDetails userDetails = jwtUtil.parseToken(jwt.get());
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (CasServiceException ignored) {
                // TODO: Remove log in production
                log.error("Failed authentication : {}", ignored.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
