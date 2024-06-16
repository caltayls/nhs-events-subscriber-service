package com.caltayls.nhs_events_subscribers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;

@Service
public interface UserService {
  UserResponse createUser(UserRequest request);
  List<UserResponse> getAllUsers();
  UserResponse getUserByEmail(String email);
  UserResponse updateUser(String email, UserRequest request);
  void removeUserByEmail(String email);
}