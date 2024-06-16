package com.caltayls.nhs_events_subscribers.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String email) {
    super("User {email: " + email + "} already exists.");
  }

}