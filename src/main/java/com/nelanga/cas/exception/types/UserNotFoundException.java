package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceError;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CasServiceException {

    private static final ServiceError error = ServiceError.USER_NOT_FOUND;

    public UserNotFoundException() {
        super(error.message(), HttpStatus.NOT_FOUND, error.code());
    }
}
