package com.caltayls.nhs_events_subscribers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  String UserAlreadyExistsHandler(UserAlreadyExistsException e) {
    return e.getMessage();
  }

  @ExceptionHandler(UserDoesNotExistException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String UserDoesNotExistHandler(UserDoesNotExistException e) {
    return e.getMessage();
  }
  
}