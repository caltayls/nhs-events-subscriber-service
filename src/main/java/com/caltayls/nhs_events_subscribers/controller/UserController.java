package com.caltayls.nhs_events_subscribers.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;
import com.caltayls.nhs_events_subscribers.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class UserController {
  
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> allUsers() {
    return ResponseEntity.ok()
      .body(userService.getAllUsers());
  }

  @GetMapping("/users/{email}")
  public ResponseEntity<UserResponse> findUser(@PathVariable String email) {
    return ResponseEntity.ok()
      .body(userService.getUserByEmail(email));
  }

  @PatchMapping("/users/{email}")
  public ResponseEntity<UserResponse> updateUser(@PathVariable String email, @RequestBody UserRequest userRequest) {
    return ResponseEntity.ok()
      .body(userService.updateUser(email, userRequest));
  }

  @PostMapping("/users")
  public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.ok()
      .body(userService.createUser(userRequest));
  }

  @DeleteMapping("/users/{email}")
  public ResponseEntity<Void> removeUser(@PathVariable String email) {
    userService.removeUserByEmail(email);
    return ResponseEntity.noContent().build();      
  }

}