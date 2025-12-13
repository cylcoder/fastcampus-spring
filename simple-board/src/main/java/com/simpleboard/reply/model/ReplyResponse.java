package com.simpleboard.reply.model;

import com.simpleboard.reply.db.Reply;
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
public class ReplyResponse {

  private Long id;
  private Long postId;
  private String username;
  private String title;
  private String content;
  private LocalDateTime repliedAt;

  public static ReplyResponse toDto(Reply reply) {
    return ReplyResponse.builder()
        .id(reply.getId())
        .postId(reply.getPost().getId())
        .username(reply.getUsername())
        .title(reply.getTitle())
        .content(reply.getContent())
        .repliedAt(reply.getRepliedAt())
        .build();
  }

}
