package com.caltayls.nhs_events_subscribers.entity;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName="users")
public class User {

  @DynamoDBHashKey
  private String email;
  @DynamoDBAttribute
  private String emailFrequency;
  @DynamoDBAttribute
  private List<String> location;
  @DynamoDBAttribute
  private Boolean weeklyUpdate;


}