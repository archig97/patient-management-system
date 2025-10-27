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
    /*
    * exception.getBindingResult()
→ Gets the binding/validation results from the thrown exception — it contains all the validation info.
getFieldErrors()
→ Returns a List<FieldError> — one item for each invalid field in your DTO.
.forEach(error -> …)
→ Loops over that list.
error.getField()
→ Returns the name of the field that failed validation (e.g. "email").
error.getDefaultMessage()
→ Returns the human-readable message from your validation annotation (e.g. "must not be blank").
errors.put(field, message)
→ Adds that pair to your map.
    * */
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email address already exists");
        return ResponseEntity.badRequest().body(errors);

    }
}
