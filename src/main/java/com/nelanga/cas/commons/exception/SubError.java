package com.nelanga.cas.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubError {

    private String errorMessage;
    private String errorSource;
}
