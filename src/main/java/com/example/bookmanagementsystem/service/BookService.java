package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.dto.BookDTO;
import com.example.bookmanagementsystem.dto.BookListSearchDTO;
import com.example.bookmanagementsystem.dto.BorrowedBookListDTO;
import com.example.bookmanagementsystem.dto.SingleBookSearchDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * @program: book-management-system
 * @description: 图书业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface BookService {

    @Transactional
    Map<String, Object> findBooksByTitleContaining(String title);

    @Transactional
    Map<String, Object> findBooksByAuthorContaining(String author);

    @Transactional
    Map<String, Object> findBooksByPublishingHouseContaining(String publishingHouse);

    @Transactional
    Map<String, Object> findBooksByIsbnContaining(String isbn);

    @Transactional
    Map<String, Object> saveBook(BookDTO bookDTO);

    @Transactional
    Map<String, Object> getBookByBookSearch(SingleBookSearchDTO singleBookSearchDTO);

    @Transactional
    Map<String, Object> getBookListSearch(Integer page, Integer size, BookListSearchDTO bookListSearchDTO);

    @Transactional
    Map<String, Object> borrowBooks(BorrowedBookListDTO borrowedBookListDTO);

    @Transactional
    Map<String, Object> returnBooks(BorrowedBookListDTO borrowedBookListDTO);

    @Transactional
    Map<String, Object> renewBooks(BorrowedBookListDTO borrowedBookListDTO);

}