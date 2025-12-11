package com.simpleboard.post.model;

import com.simpleboard.post.db.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
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

  public static Post toEntity(PostRequest postRequest) {
    return Post.builder()
        .board(null)
        .username(postRequest.getUsername())
        .password(postRequest.getPassword())
        .email(postRequest.getEmail())
        .status(PostStatus.REGISTERED)
        .title(postRequest.getTitle())
        .content(postRequest.getContent())
        .postedAt(LocalDateTime.now())
        .build();
  }

}
