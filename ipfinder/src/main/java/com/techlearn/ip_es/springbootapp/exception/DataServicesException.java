package com.techlearn.ip_es.springbootapp.exception;

public class DataServicesException extends Exception {

    private static final long serialVersionUID = 3227648002512343219L;

    private final DataServicesErrorCode code;

    public DataServicesException(DataServicesErrorCode code) {
        super();
        this.code = code;
    }
    public DataServicesException(String message, Throwable cause, DataServicesErrorCode code) {
        super(message, cause);
        this.code = code;
    }
    public DataServicesException(String message, DataServicesErrorCode code) {
        super(message);
        this.code = code;
    }

}
