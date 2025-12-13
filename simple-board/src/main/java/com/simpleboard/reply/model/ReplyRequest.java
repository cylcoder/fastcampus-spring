package com.simpleboard.reply.model;

import com.simpleboard.common.Status;
import com.simpleboard.post.db.Post;
import com.simpleboard.reply.db.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReplyRequest {

  @NotNull
  private Long postId;

  @NotBlank
  private String username;

  @NotBlank
  @Size(min = 4, max = 4)
  private String password;

  @NotBlank
  private String title;

  @NotBlank
  private String content;

  public static Reply toEntity(ReplyRequest replyRequest, Post post) {
    return Reply.builder()
        .post(post)
        .username(replyRequest.getUsername())
        .password(replyRequest.getPassword())
        .title(replyRequest.getTitle())
        .content(replyRequest.getContent())
        .status(Status.REGISTERED)
        .repliedAt(LocalDateTime.now())
        .build();
  }

}
