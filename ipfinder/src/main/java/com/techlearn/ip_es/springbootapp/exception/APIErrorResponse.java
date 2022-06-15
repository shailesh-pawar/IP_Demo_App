package com.techlearn.ip_es.springbootapp.exception;

public class APIErrorResponse {
    private String errorCode;
    private String errorMessage;

    public APIErrorResponse(DataServicesErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode.name();
        this.errorMessage = errorMessage;
    }

    public APIErrorResponse(String errorMessage) {
        this.errorCode = errorMessage;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
