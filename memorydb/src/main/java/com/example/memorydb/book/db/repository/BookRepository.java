package com.example.memorydb.book.db.repository;

import com.example.memorydb.book.db.entity.Book;
import com.example.memorydb.db.SimpleDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
