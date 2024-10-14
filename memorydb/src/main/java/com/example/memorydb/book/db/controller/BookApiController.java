package com.example.memorydb.book.db.controller;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PutMapping
    public BookEntity create(@RequestBody BookEntity bookEntity) {
        return bookService.create(bookEntity);
    }

    @GetMapping
    public List<BookEntity> findAll() {
        return bookService.findAll();
    }

}
