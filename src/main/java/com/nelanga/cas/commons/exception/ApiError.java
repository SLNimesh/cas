package com.nelanga.cas.commons.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiError {

    private HttpStatus responseStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;
    private Integer errorCode;
    private String message;
    private final List<SubError> subErrors;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
        this.subErrors = new ArrayList<>();
    }

    public ApiError(ServiceException exception) {
        this(exception.getHttpStatus(), exception.getMessage(), exception.getErrorCode());
    }

    public ApiError(HttpStatus responseStatus, String message, Integer errorCode) {
        this();
        this.responseStatus = responseStatus;
        this.message = message;
        this.errorCode = errorCode;
    }

    public void addSubErrors(List<SubError> subErrors) {
        this.subErrors.addAll(subErrors);
    }
}
