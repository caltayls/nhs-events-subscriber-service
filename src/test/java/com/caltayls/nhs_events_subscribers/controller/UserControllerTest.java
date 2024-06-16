package com.caltayls.nhs_events_subscribers.controller;

import static org.hamcrest.Matchers.contains;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.caltayls.nhs_events_subscribers.TestData.TEST_EMAIL_FREQUENCY;
import static com.caltayls.nhs_events_subscribers.TestData.TEST_LOCATION;
import static com.caltayls.nhs_events_subscribers.TestData.TEST_USER_EMAIL;
import static com.caltayls.nhs_events_subscribers.TestData.TEST_WEEKLY_UPDATE;
import static com.caltayls.nhs_events_subscribers.TestData.aUserRequest;
import static com.caltayls.nhs_events_subscribers.TestData.aUserResponse;
import com.caltayls.nhs_events_subscribers.request.UserRequest;
import com.caltayls.nhs_events_subscribers.response.UserResponse;
import com.caltayls.nhs_events_subscribers.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

// TODO: add exception cases 

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;

  @Test
  void givenUserEmail_whenGetRequest_thenRetrieveUser() throws Exception {
    // Given
    UserResponse userResponse = aUserResponse();
    // When
    when(userService.getUserByEmail(TEST_USER_EMAIL)).thenReturn(userResponse);
    // Then
    mockMvc.perform(get("/api/v1/users/" + TEST_USER_EMAIL))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpectAll(
        jsonPath("$.email").value(TEST_USER_EMAIL),
        jsonPath("$.emailFrequency").value(TEST_EMAIL_FREQUENCY),
        jsonPath("$.location").value(contains(TEST_LOCATION.toArray())),
        jsonPath("$.weeklyUpdate").value(TEST_WEEKLY_UPDATE)
      );
  }

  @Test
  void givenUserEmail_whenUpdateEmailFrequency_thenRetrieveUser() throws Exception {
    // Given
    String email = TEST_USER_EMAIL;
    String newFrequency = "weekly";
    UserResponse userResponse = aUserResponse();
    userResponse.setEmailFrequency(newFrequency);

    UserRequest userRequest = UserRequest.builder()
      .emailFrequency(newFrequency)
      .build();

    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(userRequest);

    // When
    when(userService.updateUser(email, userRequest)).thenReturn(userResponse);
    // Then
    mockMvc.perform(
      patch("/api/v1/users/" + TEST_USER_EMAIL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpectAll(
        jsonPath("$.email").value(TEST_USER_EMAIL),
        jsonPath("$.emailFrequency").value(newFrequency),
        jsonPath("$.location").value(contains(TEST_LOCATION.toArray())),
        jsonPath("$.weeklyUpdate").value(TEST_WEEKLY_UPDATE)
      );
  }
  
  @Test
  void givenUserRequest_whenCreateUser_thenCreateUser() throws Exception {
    // Given
    UserResponse userResponse = aUserResponse();
    UserRequest userRequest = aUserRequest();

    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(userRequest);

    // When
    when(userService.createUser(userRequest)).thenReturn(userResponse);
    // Then
    mockMvc.perform(
      post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json"))
      .andExpectAll(
        jsonPath("$.email").value(TEST_USER_EMAIL),
        jsonPath("$.emailFrequency").value(TEST_EMAIL_FREQUENCY),
        jsonPath("$.location").value(contains(TEST_LOCATION.toArray())),
        jsonPath("$.weeklyUpdate").value(TEST_WEEKLY_UPDATE)
      );
  }

  @Test
  void givenUserEmail_whenRemoveUser_thenRemoveUser() throws Exception {
    // Given
    String email = TEST_USER_EMAIL;
    // When
    // Then
    mockMvc.perform(delete("/api/v1/users/" + email))
      .andExpect(status().is2xxSuccessful());
  }


}