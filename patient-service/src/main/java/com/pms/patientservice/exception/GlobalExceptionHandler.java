package com.pms.patientservice.exception;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//helps to catch and format exceptions before being thrown to clients
@ControllerAdvice //handles any exception thrown by controllers in this project
public class GlobalExceptionHandler {

    //MethodArgumentNotValidException generally happens when @Valid annotation used to vet request body
    //or to check annotations like @Email or @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)//If any controller throws a MethodArgumentNotValidException, run this method instead of returning a raw error.
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException exception) { //the exception variable will hold all data about what fields failed and why
    Map<String, String> errors = new HashMap<>();//collects all the errors
        //extract and collect validation errors
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
