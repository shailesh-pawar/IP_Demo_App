package com.techlearn.ip_es.springbootapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = NotfoundException.class)
    public ResponseEntity<Object> exception(NotfoundException exception) {
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataServicesException.class)
    public ResponseEntity<Object> exception(DataServicesException exception) {
        return new ResponseEntity<>("DataServiceException", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}