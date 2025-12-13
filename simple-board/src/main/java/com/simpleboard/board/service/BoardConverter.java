package com.simpleboard.board.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardResponse;
import com.simpleboard.crud.Converter;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter implements Converter<Board, BoardRequest, BoardResponse> {

  @Override
  public Board toEntity(BoardRequest boardRequest) {
    return BoardRequest.toEntity(boardRequest);
  }

  @Override
  public BoardResponse toDto(Board board) {
    return BoardResponse.toDto(board);
  }

}
