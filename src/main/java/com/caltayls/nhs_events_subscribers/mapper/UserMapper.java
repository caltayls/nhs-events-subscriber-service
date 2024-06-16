package com.caltayls.nhs_events_subscribers.mapper;

import org.springframework.stereotype.Component;

import com.caltayls.nhs_events_subscribers.entity.User;
import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;

@Component
public class UserMapper {
  public UserResponse toDto(User user) {
    return UserResponse.builder()
      .email(user.getEmail())
      .emailFrequency(user.getEmailFrequency())
      .location(user.getLocation())
      .weeklyUpdate(user.getWeeklyUpdate())
      .build();
  }

  public User toModel(UserRequest request) {
    return User.builder()
      .email(request.getEmail())
      .emailFrequency(request.getEmailFrequency())
      .location(request.getLocation())
      .weeklyUpdate(request.getWeeklyUpdate())
      .build();
  }
}