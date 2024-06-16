package com.caltayls.nhs_events_subscribers.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="users")
public class User {

  @Id
  private String email;

  private String emailFrequency;

  private List<String> location;
  
  private Boolean weeklyUpdate;


}