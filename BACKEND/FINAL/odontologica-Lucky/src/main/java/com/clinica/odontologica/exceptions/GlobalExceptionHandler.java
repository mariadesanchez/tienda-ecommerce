package com.dh.clinica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.apache.log4j.*;


@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resuelveResourceNotFoundException(ResourceNotFoundException err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje : " + err.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorAll(Exception ex, WebRequest req)
    {
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
