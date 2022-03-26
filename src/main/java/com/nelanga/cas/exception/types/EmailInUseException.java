package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import org.springframework.http.HttpStatus;

public class EmailInUseException extends CasServiceException {

    private static final ServiceErrorType error = ServiceErrorType.EMAIL_IN_USE;

    public EmailInUseException() {
        super(error.message(), HttpStatus.NOT_FOUND, error.code());
    }
}
