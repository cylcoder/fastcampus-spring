package com.example.memorydb.book.db.controller;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookApiController {

  private final BookService bookService;

  @PostMapping
  public BookEntity create(@RequestBody BookEntity bookEntity) {
    return bookService.create(bookEntity);
  }

  @GetMapping
  public List<BookEntity> findAll() {
    return bookService.findAll();
  }

}
