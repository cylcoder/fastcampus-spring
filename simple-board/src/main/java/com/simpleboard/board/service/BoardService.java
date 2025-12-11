package com.simpleboard.board.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public Board create(BoardRequest request) {
    Board board = Board.builder()
        .name(request.getName())
        .status(BoardStatus.REGISTERED)
        .build();

    return boardRepository.save(board);
  }

}
