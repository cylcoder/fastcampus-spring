package com.simpleboard.board.controller;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardResponse;
import com.simpleboard.board.service.BoardService;
import com.simpleboard.crud.CRUDApiController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
public class BoardController extends CRUDApiController<Board, BoardRequest, BoardResponse> {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    super(boardService);
    this.boardService = boardService;
  }

}
