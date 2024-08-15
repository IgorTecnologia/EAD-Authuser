package com.ead.authuser.resources.exception;

import com.ead.authuser.services.exception.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError();

        HttpStatus status = HttpStatus.NOT_FOUND;

        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Entity not found exception.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<StandardError> passwordException(PasswordException e, HttpServletRequest request){

        StandardError err = new StandardError();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Bad request password.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<StandardError> signupException(SignupException e, HttpServletRequest request){

        StandardError err = new StandardError();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Bad registration request!");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
