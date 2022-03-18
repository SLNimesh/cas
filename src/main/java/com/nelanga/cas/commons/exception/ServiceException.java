package com.nelanga.cas.commons.exception;

import com.nelanga.cas.commons.enums.ServiceError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    /*
     * As RuntimeException is a Serializable class
     * */
    static final long serialVersionUID = 789456123L;

    static final ServiceError error = ServiceError.UNIDENTIFIED;

    private final HttpStatus httpStatus;
    private final Integer errorCode;

    public ServiceException() {
        this(error.message());
    }

    public ServiceException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = error.code();
    }

    public ServiceException(String message, Integer errorCode) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = errorCode;
    }

    public ServiceException(String message, HttpStatus httpStatus, Integer errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public ServiceException(Exception exception) {
        this(exception.getLocalizedMessage());
    }
}
