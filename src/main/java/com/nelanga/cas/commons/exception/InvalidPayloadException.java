package com.nelanga.cas.commons.exception;

import com.nelanga.cas.commons.enums.ServiceError;
import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends ServiceException{

    private static final ServiceError error = ServiceError.VALIDATION_FAILED;

    public InvalidPayloadException() {
        super(error.message(), HttpStatus.BAD_REQUEST, error.code());
    }
}
