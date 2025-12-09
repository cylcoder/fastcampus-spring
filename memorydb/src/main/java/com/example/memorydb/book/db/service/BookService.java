package com.example.memorydb.book.db.service;

import com.example.memorydb.book.db.entity.Book;
import com.example.memorydb.book.db.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Book create(Book book) {
    return bookRepository.save(book);
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

}
