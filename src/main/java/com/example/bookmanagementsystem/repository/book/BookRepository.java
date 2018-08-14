package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
