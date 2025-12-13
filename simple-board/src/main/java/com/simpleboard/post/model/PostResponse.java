package com.simpleboard.post.model;

import com.simpleboard.common.Status;
import com.simpleboard.post.db.Post;
import com.simpleboard.reply.model.ReplyResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class PostResponse {

  private Long id;

  private Long boardId;

  private String username;

  private String email;

  private String title;

  private String content;

  private Status status;

  @Builder.Default
  private List<ReplyResponse> replies = new ArrayList<>();
  private LocalDateTime postedAt;

  public static PostResponse toDto(Post post) {
    return PostResponse.builder()
        .id(post.getId())
        .boardId(post.getBoard().getId())
        .username(post.getUsername())
        .email(post.getEmail())
        .title(post.getTitle())
        .content(post.getContent())
        .status(post.getStatus())
        .replies(post.getReplies()
            .stream()
            .map(ReplyResponse::toDto)
            .toList())
        .postedAt(post.getPostedAt())
        .build();
  }

}
