package com.nelanga.cas.commons.enums;

public enum ServiceError {

    /*
    * TODO: Complete list of errorCodes
    * */
    UNIDENTIFIED(0, "Something went wrong on our end"),

    CAS_ERROR(10, "Something went wrong in Authentication System"),
    AUTH_FAILED(11, "Authentication Failed"),

    USER_NOT_FOUND(41, "Failed to identify user");

    private final Integer errorCode;
    private final String errorMessage;

    ServiceError(Integer errorCode, String message) {
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
