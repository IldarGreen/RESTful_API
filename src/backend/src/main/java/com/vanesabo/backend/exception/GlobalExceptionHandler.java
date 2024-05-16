package com.vanesabo.backend.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.ErrorResponse;


import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Так выведет только текст ошибки
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

//    psql -h hostname -p 5432 -d postgres -U postgres -W postgres

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<Object> handleMethodNotFound(
//            ResponseStatusException ex, WebRequest request) {
//        String errorMessage = ex.  . .getMessage().  .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining("; "));
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<Object> handleMethodNotFound(
//            ResponseStatusException ex, WebRequest request) {
//        String errorMessage = ex.  . .getMessage().  .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining("; "));
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ErrorMessage(exception.getMessage()));
//    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ErrorResponse noResult(Exception e) {
//        System.out.println("An exception has happened");
//        return ErrorResponse.builder(e, HttpStatusCode.valueOf(500), "NotFoundException")
//                .build();
//    }

//    // Handle two or more exceptions
//    @ExceptionHandler({ChangeSetPersister.NotFoundException.class, NullPointerException.class})
//    ErrorResponse noResult(Exception e) {
//        System.out.println("An exception has happened");
//        return ErrorResponse.builder(e, HttpStatusCode.valueOf(500), "NullPointerException or NotFoundException")
//                .build();
//    }

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<Object> notFoundException(
//            ResponseStatusException ex, WebRequest request) {
//        String errorMessage = ex.getRootCause().getMessage();
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
////        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ErrorMessage(exception.getMessage()));
//    }

}

////так выведет JSON с полем и описанием ошибки
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }
//}