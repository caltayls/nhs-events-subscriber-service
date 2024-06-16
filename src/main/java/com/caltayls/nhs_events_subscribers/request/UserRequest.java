package com.caltayls.nhs_events_subscribers.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
  String email;
  String emailFrequency;
  List<String> location;
  Boolean weeklyUpdate;
}
