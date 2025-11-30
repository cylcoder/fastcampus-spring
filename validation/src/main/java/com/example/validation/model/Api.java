package com.example.validation.model;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

  private Integer resultCode;

  private String resultMessage;

  @Valid
  private T data;

  private Error error;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class Error {
    private List<String> errorMessages;
  }

}
