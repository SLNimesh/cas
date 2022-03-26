package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import org.springframework.http.HttpStatus;

public class AuthFailedException extends CasServiceException {

    private static final ServiceErrorType error = ServiceErrorType.AUTH_FAILED;

    public AuthFailedException() {
        super(error.message(), HttpStatus.UNAUTHORIZED, error.code());
    }
}
