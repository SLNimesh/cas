package com.nelanga.cas.commons.support;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AppAuditor implements AuditorAware<String> {

    /*
    * TODO: Use custom logic if necessary
    * */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName()
        );
    }
}
