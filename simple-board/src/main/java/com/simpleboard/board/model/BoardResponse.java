package com.simpleboard.board.model;

import com.simpleboard.common.Status;
import com.simpleboard.board.db.Board;
import com.simpleboard.post.model.PostResponse;
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
public class BoardResponse {

  private Long id;

  private String name;

  private Status status;

  @Builder.Default
  private List<PostResponse> posts = new ArrayList<>();

  public static BoardResponse toDto(Board board) {
    return BoardResponse.builder()
        .id(board.getId())
        .name(board.getName())
        .status(board.getStatus())
        .posts(
            board.getPosts()
            .stream()
            .map(PostResponse::toDto)
            .toList()
        )
        .build();
  }

}
