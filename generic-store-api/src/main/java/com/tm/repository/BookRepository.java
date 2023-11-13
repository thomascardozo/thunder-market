package com.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tm.model.Book;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(UUID bookId);
}