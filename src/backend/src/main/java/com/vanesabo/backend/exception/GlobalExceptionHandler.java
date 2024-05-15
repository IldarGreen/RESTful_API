package com.vanesabo.backend.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Так выведет только текст ошибки
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<Object> handleMethodNotFound(
//            MethodArgumentNotValidException ex, WebRequest request) {
//        String errorMessage = ex.getBindingResult().getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining("; "));
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> notFoundException(
            NoHandlerFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>("zdfggfgfgfdgfgdfgdfgd", HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

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