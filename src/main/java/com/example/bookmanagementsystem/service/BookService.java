package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.BookDTO;

import java.util.Map;

public interface BookService {

    Map<String, Object> findBooksByTitleContaining(String title);

    Map<String, Object> findBooksByAuthorContaining(String author);

    Map<String, Object> findBooksByPublishingHouseContaining(String publishingHouse);

    Map<String, Object> findBooksByIsbnContaining(String isbn);

    Map<String, Object> saveBook(BookDTO bookDTO);
}