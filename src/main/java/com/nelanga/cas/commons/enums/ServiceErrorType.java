package com.nelanga.cas.commons.enums;

public enum ServiceErrorType {

    /*
    * TODO: Complete list of errorCodes
    * */
    UNIDENTIFIED(0, "Something went wrong on our end"),
    VALIDATION_FAILED(1, "Request payload seems to be invalid"),

    CAS_ERROR(10, "Something went wrong in Authentication System"),
    AUTH_FAILED(11, "Authentication Failed"),

    USER_NOT_FOUND(41, "Failed to identify user"),
    EMAIL_IN_USE(42, "Email already in use. Please sign in"),
    USERNAME_TAKEN(43, "Username already taken. Please try a different one");

    private final Integer errorCode;
    private final String errorMessage;

    ServiceErrorType(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public Integer code() {
        return errorCode;
    }

    public String message() {
        return errorMessage;
    }
}
