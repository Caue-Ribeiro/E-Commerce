package com.caue.democommerce.controllers.handlers;

import com.caue.democommerce.dto.CustomError;
import com.caue.democommerce.dto.ValidationError;
import com.caue.democommerce.services.exceptions.DatabaseException;
import com.caue.democommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND ;

        CustomError error = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;

        CustomError error = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e,
                                                          HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;

        ValidationError err = new ValidationError(Instant.now(),status.value(),"Invalid data",request.getRequestURI());

                e.getBindingResult()
                        .getFieldErrors()
                        .forEach(fieldError -> err.addError(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()));

        return ResponseEntity.status(status).body(err);
    }
}
