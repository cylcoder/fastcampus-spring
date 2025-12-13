package com.example.filter.model;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRequest(
    String name,
    String phoneNumber,
    String email,
    Integer age
) {

  public UserRequest withPhoneNumber(String phoneNumber) {
    return new UserRequest(name, phoneNumber, email, age);
  }

}
