package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import org.springframework.http.HttpStatus;

public class UsernameTakenException extends CasServiceException {

    public static final ServiceErrorType error = ServiceErrorType.USERNAME_TAKEN;

    public UsernameTakenException() {
        super(error.message(), HttpStatus.NOT_FOUND, error.code());
    }
}
