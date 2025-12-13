package com.simpleboard.board.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardResponse;
import com.simpleboard.crud.CRUDService;
import com.simpleboard.crud.Converter;
import org.springframework.stereotype.Service;

@Service
public class BoardService extends CRUDService<Board, BoardRequest, BoardResponse> {

  private final BoardRepository boardRepository;
  private final Converter<Board, BoardRequest, BoardResponse> converter;

  public BoardService(
      BoardRepository boardRepository,
      Converter<Board, BoardRequest, BoardResponse> converter
  ) {
    super(boardRepository, converter);
    this.boardRepository = boardRepository;
    this.converter = converter;
  }

}
