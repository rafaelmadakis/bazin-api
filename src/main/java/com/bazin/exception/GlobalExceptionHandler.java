package com.bazin.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidInputException.class)
  public ResponseEntity<?> handleInvalid(InvalidInputException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "invalid_input", "message", ex.getMessage()));
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<?> handleUnauth(UnauthorizedException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "unauthorized", "message", ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAll(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "internal_error", "message", ex.getMessage()));
  }
}