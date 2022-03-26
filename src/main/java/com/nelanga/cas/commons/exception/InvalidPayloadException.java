package com.nelanga.cas.commons.exception;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends ServiceException{

    private static final ServiceErrorType error = ServiceErrorType.VALIDATION_FAILED;

    public InvalidPayloadException() {
        super(error.message(), HttpStatus.BAD_REQUEST, error.code());
    }
}
