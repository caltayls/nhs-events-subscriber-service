package com.caltayls.nhs_events_subscribers.exception;

public class UserDoesNotExistException extends RuntimeException {

  public UserDoesNotExistException(String email) {
    super("User {email: " + email + "} does not exist.");
  }

}