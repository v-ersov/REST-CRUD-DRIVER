package com.rest.crud.restcrudexample.controller;

import com.rest.crud.restcrudexample.dto.ErrorResponse;
import com.rest.crud.restcrudexample.exception.DriverNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDriverNotFoundException(DriverNotFoundException ex) {
        log.error("DriverNotFoundException cause: {}", ex.getMessage());
        var notFound = HttpStatus.NOT_FOUND;
        var errorResponse = new ErrorResponse(notFound.value(), ex.getMessage());
        return ResponseEntity.status(notFound).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException cause: {}", ex.getMessage());
        var notFound = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(notFound.value(), ex.getMessage());
        return ResponseEntity.status(notFound).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException cause: {}", ex.getMessage());
        var errorFields = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorFields.put(fieldError.getField(), fieldError.getDefaultMessage()));
        var notFound = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(notFound.value(), errorFields);
        return ResponseEntity.status(notFound).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException cause: {}", ex.getMessage());
        var notFound = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponse(notFound.value(), ex.getMessage());
        return ResponseEntity.status(notFound).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Exception cause: {}", ex.getMessage(), ex);
        var internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new ErrorResponse(internalServerError.value(), ex.getMessage());
        return ResponseEntity.status(internalServerError).body(errorResponse);
    }
}
