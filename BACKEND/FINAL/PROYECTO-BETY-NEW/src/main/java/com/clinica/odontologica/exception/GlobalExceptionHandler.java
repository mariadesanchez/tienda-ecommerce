package com.clinica.odontologica.exception;

import com.clinica.odontologica.OdontologicaApplication;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {
//    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    Logger logger = LoggerFactory.getLogger(OdontologicaApplication.GlobalExceptionHandler.class);

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
