package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceError;
import org.springframework.http.HttpStatus;

public class AuthFailedException extends CasServiceException {

    private static final ServiceError error = ServiceError.AUTH_FAILED;

    public AuthFailedException() {
        super(error.message(), HttpStatus.UNAUTHORIZED, error.code());
    }
}
