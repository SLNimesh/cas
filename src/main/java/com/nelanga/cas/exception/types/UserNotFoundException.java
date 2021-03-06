package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CasServiceException {

    private static final ServiceErrorType error = ServiceErrorType.USER_NOT_FOUND;

    public UserNotFoundException() {
        super(error.message(), HttpStatus.NOT_FOUND, error.code());
    }
}
