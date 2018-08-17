package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.BookDTO;
import com.example.bookmanagementsystem.dto.SingleBookSearchDTO;

import java.util.Map;
/**
 * @program: book-management-system
 * @description: 图书业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface BookService {

    Map<String, Object> findBooksByTitleContaining(String title);

    Map<String, Object> findBooksByAuthorContaining(String author);

    Map<String, Object> findBooksByPublishingHouseContaining(String publishingHouse);

    Map<String, Object> findBooksByIsbnContaining(String isbn);

    Map<String, Object> saveBook(BookDTO bookDTO);

    Map<String, Object> getBookByBookSearch(SingleBookSearchDTO singleBookSearchDTO);

}