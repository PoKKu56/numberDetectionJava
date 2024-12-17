package ru.glazunov.numberDetectionJava.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.glazunov.numberDetectionJava.exception.NoFileException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoFileException.class)
    public ResponseEntity<?> handleNoFileException(final NoFileException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
    }

}
