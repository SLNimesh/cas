package com.nelanga.cas.exception;

import com.nelanga.cas.commons.exception.ApiError;
import com.nelanga.cas.commons.exception.ServiceException;
import com.nelanga.cas.exception.types.CasServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CasExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CasServiceException.class)
    protected ResponseEntity<Object> handleCASException(CasServiceException exception) {
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception exception) {
        // TODO: Just send a ServiceException() without the localized msg
        log.error("Unhandled exception {}", exception.getMessage());
        return buildResponseEntity(new ServiceException(exception));
    }

    private ResponseEntity<Object> buildResponseEntity(ServiceException serviceException) {
        ApiError apiError = new ApiError(serviceException);
        return new ResponseEntity<>(apiError, apiError.getResponseStatus());
    }
}
