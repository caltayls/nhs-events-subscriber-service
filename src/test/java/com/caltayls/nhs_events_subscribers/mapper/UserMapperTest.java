package com.caltayls.nhs_events_subscribers.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static com.caltayls.nhs_events_subscribers.TestData.aUser;
import com.caltayls.nhs_events_subscribers.entity.User;
import com.caltayls.nhs_events_subscribers.response.UserResponse;


public  class UserMapperTest {
  UserMapper userMapper = new UserMapper();

  @Test
  void givenUser_whenToDto_thenSuccessfullyMapResponse() {
    // Given
    final User user = aUser();
    // When
    final UserResponse userResponse = userMapper.toDto(user);
    // Then
    assertEquals(user.getEmail(), userResponse.getEmail());
    assertEquals(user.getEmailFrequency(), userResponse.getEmailFrequency());
    assertEquals(user.getLocation(), userResponse.getLocation());
    assertEquals(user.getWeeklyUpdate(), userResponse.getWeeklyUpdate());

  }

}
