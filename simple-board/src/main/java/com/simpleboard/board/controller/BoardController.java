package com.simpleboard.board.controller;

import com.simpleboard.board.model.BoardRequest;
import com.simpleboard.board.model.BoardResponse;
import com.simpleboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public BoardResponse create(@Valid @RequestBody BoardRequest boardRequest) {
    return boardService.create(boardRequest);
  }

  @GetMapping("/{id}")
  public BoardResponse findById(@PathVariable Long id) {
    return boardService.findById(id);
  }

}
