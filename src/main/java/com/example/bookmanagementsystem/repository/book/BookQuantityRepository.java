package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.book.Book;
import com.example.bookmanagementsystem.entity.book.BookQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookQuantityRepository extends JpaRepository<BookQuantity, Long> {

    BookQuantity findBookQuantityByBook(Book book);
}
