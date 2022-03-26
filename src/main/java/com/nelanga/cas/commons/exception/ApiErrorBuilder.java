package com.nelanga.cas.commons.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

public class ApiErrorBuilder {

    public static ApiError build(ServiceException exception) {
        return new ApiError(exception);
    }

    public static ApiError build(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<SubError> subErrors = fieldErrors.stream()
                .map(fieldError -> new SubError(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(new InvalidPayloadException());
        apiError.addSubErrors(subErrors);
        return apiError;
    }
}
