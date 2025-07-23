package com.nevitech.campaign_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

    @ControllerAdvice
    public class ValidationExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<Map<String, String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Geçersiz HTTP methodu: " + ex.getMethod());
            return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
        }
        @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
        public ResponseEntity<Map<String, String>> handleIllegalExceptions(RuntimeException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }