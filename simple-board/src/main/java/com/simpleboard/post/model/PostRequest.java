package com.simpleboard.post.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class PostRequest {

  @NotBlank
  private String username;

  @NotBlank
  @Size(min = 4, max = 4)
  private String password;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String title;


  private String content;

}
