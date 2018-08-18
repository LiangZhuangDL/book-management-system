package com.example.bookmanagementsystem.repository.book;

import com.example.bookmanagementsystem.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitleAndAuthorAndPublishingHouseAndIsbn(String title, String author, String publishingHouse, String isbn);

    Book findBookByTitleAndAuthorAndIsbnAndNumber(String title, String author, String isbn, String number);

    Page<Book> findBooksByAuthorContaining(String author, Pageable pageable);

    Page<Book> findBooksByTitleContaining(String title, Pageable pageable);

    Page<Book> findBooksByPublishingHouseContaining(String publishingHouse, Pageable pageable);

}
