package com.caltayls.nhs_events_subscribers.service;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.caltayls.nhs_events_subscribers.TestData.aUser;
import static com.caltayls.nhs_events_subscribers.TestData.aUserRequest;
import static com.caltayls.nhs_events_subscribers.TestData.aUserResponse;
import com.caltayls.nhs_events_subscribers.entity.User;
import com.caltayls.nhs_events_subscribers.exception.UserAlreadyExistsException;
import com.caltayls.nhs_events_subscribers.exception.UserDoesNotExistException;
import com.caltayls.nhs_events_subscribers.mapper.UserMapper;
import com.caltayls.nhs_events_subscribers.repository.UserRepository;
import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;
import static com.caltayls.nhs_events_subscribers.TestData.TEST_USER_EMAIL;

@ContextConfiguration(classes = { UserServiceImpl.class })
@ExtendWith(SpringExtension.class)
class UserServiceTest {
  
  @MockBean
  private UserRepository userRepository;

  @MockBean
  private UserMapper userMapper;
  
  @Autowired
  private UserServiceImpl userService;

  private final User user = aUser();
  private final UserRequest userRequest = aUserRequest();
  private final UserResponse userResponse = aUserResponse();
  
  @Test
  void givenValidUserRequest_whenCreateUser_thenReturnUserResponse() {
    // When
    when(userRepository.findByEmail(userRequest.getEmail()))
      .thenReturn(Optional.empty());
    when(userRepository.save(user)).thenReturn(user);
    when(userMapper.toDto(any())).thenReturn(userResponse);
    final UserResponse actualUserResponse = userService.createUser(userRequest);
    // Then 
    assertEquals(userResponse.getEmail(), actualUserResponse.getEmail());  
  }

  @Test
  void givenExistingUser_whenCreateUser_thenException() {
    // When Then
    when(userRepository.findByEmail(userRequest.getEmail()))
      .thenReturn(Optional.of(user));
    assertThrows(UserAlreadyExistsException.class, 
        () -> userService.createUser(userRequest));
  }

  @Test
  void givenValidEmail_whenGetUser_thenReturnUserResponse() {
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(userResponse);
    UserResponse actualUserResponse = userService.getUserByEmail(user.getEmail());
    // Then 
    assertEquals(user.getEmail(), actualUserResponse.getEmail());
    assertEquals(user.getLocation(), actualUserResponse.getLocation());
  }

  @Test
  void givenInvalidEmail_whenGetUser_thenException() {
    // Given
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
    // Then 
    assertThrows(UserDoesNotExistException.class, 
      () -> userService.getUserByEmail(TEST_USER_EMAIL));
  }

  @Test
  void givenValidUser_whenUpdateUser_thenReturnUserResponse() {
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    when(userRepository.save(user)).thenReturn(user);
    when(userMapper.toDto(user)).thenReturn(userResponse);
    UserResponse actualUserResponse = userService.updateUser(TEST_USER_EMAIL, userRequest);
    // Then
    assertEquals(user.getEmail(), actualUserResponse.getEmail());  
  }

  @Test
  void givenInvalidUser_whenUpdateUser_thenException() {
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
    // Then 
    assertThrows(UserDoesNotExistException.class, 
      () -> userService.getUserByEmail(TEST_USER_EMAIL));
  } 

  @Test
  void givenValidEmail_whenDeleteUser_thenVoid() {
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    userService.removeUserByEmail(TEST_USER_EMAIL);
    // Then
    verify(userRepository).delete(user);
  }

  @Test
  void givenInvalidEmail_whenDeleteUser_thenException() {
    // When
    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
    // Then 
    assertThrows(UserDoesNotExistException.class, 
    () -> userService.getUserByEmail(TEST_USER_EMAIL));
  }
}