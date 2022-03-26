package com.nelanga.cas.exception.types;

import com.nelanga.cas.commons.enums.ServiceErrorType;
import com.nelanga.cas.commons.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class CasServiceException extends ServiceException {

    private static final ServiceErrorType error = ServiceErrorType.CAS_ERROR;

    public CasServiceException() {
        this(error.message());
    }

    public CasServiceException(String message) {
        super(message, error.code());
    }

    public CasServiceException(String message, HttpStatus httpStatus, Integer errorCode) {
        super(message, httpStatus, errorCode);
    }
}
