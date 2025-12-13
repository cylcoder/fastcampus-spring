package com.simpleboard.board.model;

import com.simpleboard.common.Status;
import com.simpleboard.board.db.Board;
import jakarta.validation.constraints.NotBlank;
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
public class BoardRequest {

  @NotBlank
  private String name;

  public static Board toEntity(BoardRequest boardRequest) {
    return Board.builder()
        .name(boardRequest.getName())
        .status(Status.REGISTERED)
        .build();
  }

}
