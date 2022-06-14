package com.techlearn.ip_es.springbootapp.exception;

import org.springframework.http.HttpStatus;

public enum DataServicesErrorCode {

    AUTHENTICATION_ERROR(401),
    OBJECT_NOT_FOUND(404),
    INVALID_REQUEST(400),
    INTERNAL_ERROR(500);

    private final HttpStatus httpStatus;
    private final int errorCode;

    DataServicesErrorCode(int httpStatusCode) {
        this.errorCode = httpStatusCode;
        this.httpStatus = HttpStatus.valueOf(httpStatusCode);
    }

    DataServicesErrorCode(int errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatusCode() {
        return this.httpStatus.value();
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
