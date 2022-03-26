package com.nelanga.cas.commons.support;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class ReadCookieUtil {

    private String JWT_COOKIE;

    public Optional<String> getJwtCookie(HttpServletRequest request) {
        return readServletCookie(request, this.JWT_COOKIE);
    }

    public Optional<String> readServletCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .map(cookie -> cookie.getValue())
                    .findAny();
        }
        return Optional.empty();
    }
}
