package com.caltayls.nhs_events_subscribers.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caltayls.nhs_events_subscribers.entity.User;
import com.caltayls.nhs_events_subscribers.exception.UserAlreadyExistsException;
import com.caltayls.nhs_events_subscribers.exception.UserDoesNotExistException;
import com.caltayls.nhs_events_subscribers.mapper.UserMapper;
import com.caltayls.nhs_events_subscribers.repository.UserRepository;
import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  final UserRepository userRepository;
  final UserMapper userMapper;

  @Override
  public UserResponse createUser(UserRequest request) {
    String email = request.getEmail();
    Optional<User> existingUser = userRepository.findByEmail(email);
    if (existingUser.isPresent()) {
      throw new UserAlreadyExistsException(email);
    }
    User user = userRepository.save(userMapper.toModel(request));
    return userMapper.toDto(user);
  }

  @Override
  public List<UserResponse> getAllUsers() {
    Iterable<User> users = userRepository.findAll();
    List<UserResponse> userResponses = StreamSupport.
      stream(users.spliterator(), false)
      .map(userMapper::toDto)
      .collect(Collectors.toList());

    return userResponses;  
  }

  @Override
  public UserResponse getUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (!user.isPresent()) {
      throw new UserDoesNotExistException(email);
    }
    return userMapper.toDto(user.get());
  }

  @Override
  public UserResponse updateUser(String email, UserRequest request) {
    Optional<User> optUser = userRepository.findByEmail(email);
    if (optUser.isEmpty()) {
      throw new UserDoesNotExistException(request.getEmail());
    }
    User user = optUser.get();
    if (request.getEmailFrequency() != null) {
      user.setEmailFrequency(request.getEmailFrequency());
    }
    if (request.getLocation() != null) {
      user.setLocation(request.getLocation());
    }
    if (request.getWeeklyUpdate() != null) {
      user.setWeeklyUpdate(request.getWeeklyUpdate());
    }
    User userUpdated = userRepository.save(user);
    
    return userMapper.toDto(userUpdated);
  }


  @Override
  public void removeUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new UserDoesNotExistException(email);
    }
    userRepository.delete(user.get());
  }


} 