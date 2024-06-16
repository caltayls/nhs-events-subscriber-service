package com.caltayls.nhs_events_subscribers;


import java.util.ArrayList;

import com.caltayls.nhs_events_subscribers.entity.User;
import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;


public final class TestData {

  public static final String TEST_USER_EMAIL = "user@email.com";
  public static final String TEST_EMAIL_FREQUENCY = "daily";
  public static final ArrayList<String> TEST_LOCATION = new ArrayList<>() {{
    add("London");
    add("Jarrow");
  }};
  public static final Boolean TEST_WEEKLY_UPDATE = true;

  public static User aUser() {
    return User.builder()
      .email(TEST_USER_EMAIL)
      .emailFrequency(TEST_EMAIL_FREQUENCY)
      .location(TEST_LOCATION)
      .weeklyUpdate(TEST_WEEKLY_UPDATE)
      .build();
  }

  public static UserRequest aUserRequest() {
    return UserRequest.builder()
      .email(TEST_USER_EMAIL)
      .emailFrequency(TEST_EMAIL_FREQUENCY)
      .location(TEST_LOCATION)
      .weeklyUpdate(TEST_WEEKLY_UPDATE)
      .build();
  }

  public static UserResponse aUserResponse() {
    return UserResponse.builder()
      .email(TEST_USER_EMAIL)
      .emailFrequency(TEST_EMAIL_FREQUENCY)
      .location(TEST_LOCATION)
      .weeklyUpdate(TEST_WEEKLY_UPDATE)
      .build();
  }
}