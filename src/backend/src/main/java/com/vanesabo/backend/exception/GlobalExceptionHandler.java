package com.vanesabo.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

//Так выведет только текст ошибки
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> notFoundException(
            ResponseStatusException ex) {
        String errorMessage = Arrays.stream(ex.getMessage()
                        .split("\""))
                .reduce((first, second) -> second)
                .orElse(null);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
