package com.nelanga.cas.exception;

import com.nelanga.cas.commons.exception.ApiError;
import com.nelanga.cas.commons.exception.ApiErrorBuilder;
import com.nelanga.cas.commons.exception.ServiceException;
import com.nelanga.cas.exception.types.CasServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CasExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CasServiceException.class)
    protected ResponseEntity<Object> handleCASException(CasServiceException exception) {
        ApiError apiError = ApiErrorBuilder.build(exception);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationIssues(MethodArgumentNotValidException exception) {
        ApiError apiError = ApiErrorBuilder.build(exception);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception exception) {
        // TODO: Just send a ServiceException() without the localized msg
        log.error("Unhandled exception {}", exception.getMessage());
        ApiError apiError = ApiErrorBuilder.build(new ServiceException(exception));
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getResponseStatus());
    }
}
