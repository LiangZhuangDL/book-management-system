package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.BookDTO;
import com.example.bookmanagementsystem.dto.SingleBookSearchDTO;
import com.example.bookmanagementsystem.entity.book.Book;

import java.util.Map;

public interface BookService {

    Map<String, Object> findBooksByTitleContaining(String title);

    Map<String, Object> findBooksByAuthorContaining(String author);

    Map<String, Object> findBooksByPublishingHouseContaining(String publishingHouse);

    Map<String, Object> findBooksByIsbnContaining(String isbn);

    Map<String, Object> saveBook(BookDTO bookDTO);

    Map<String, Object> getBookByBookSearch(SingleBookSearchDTO singleBookSearchDTO);

}