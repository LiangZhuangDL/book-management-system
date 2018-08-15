package com.example.bookmanagementsystem.service;

import java.util.Map;

public interface BookService {

    Map<String, Object> findBooksByTitleContaining(String title);

    Map<String, Object> findBooksByAuthorContaining(String author);

    Map<String, Object> findBooksByPublishinghouseContaining(String publishingHouse);

    Map<String, Object> findBooksByIsbnContaing(String isbn);
}
