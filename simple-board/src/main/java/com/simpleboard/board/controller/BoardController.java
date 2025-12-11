package com.simpleboard.board.controller;

import com.simpleboard.board.db.Board;
import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public Board create(@Valid BoardRequest request) {
    return boardService.create(request);
  }

}
