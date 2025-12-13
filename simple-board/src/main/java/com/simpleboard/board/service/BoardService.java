package com.simpleboard.board.service;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.db.BoardRepository;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardResponse create(BoardRequest boardRequest) {
    Board board = BoardRequest.toEntity(boardRequest);
    Board savedBoard = boardRepository.save(board);
    return BoardResponse.toDto(savedBoard);
  }

  public BoardResponse findById(Long id) {
    Board board = boardRepository.findById(id)
        .orElseThrow();
    return BoardResponse.toDto(board);
  }

}
