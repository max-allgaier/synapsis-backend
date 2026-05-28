package me.maxallgaier.synapsis.auth.controller;

import me.maxallgaier.synapsis.auth.service.InvalidCredentialsException;
import me.maxallgaier.synapsis.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class AuthControllerExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
        UserAlreadyExistsException e
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", "user already exists"));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(
        InvalidCredentialsException e
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("error", "invalid credentials"));
    }
}
