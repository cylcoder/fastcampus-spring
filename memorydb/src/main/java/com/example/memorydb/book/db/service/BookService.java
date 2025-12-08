package com.example.memorydb.book.db.service;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public BookEntity create(BookEntity bookEntity) {
    return bookRepository.save(bookEntity);
  }

  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }

}
