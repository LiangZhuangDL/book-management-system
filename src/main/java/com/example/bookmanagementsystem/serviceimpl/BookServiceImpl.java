package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: book-management-system
 * @description: 图书业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-15 14:50
 **/
@Service
public class BookServiceImpl implements BookService {
    @Override
    public Map<String, Object> findBooksByTitleContaining(String title) {
        return null;
    }

    @Override
    public Map<String, Object> findBooksByAuthorContaining(String author) {
        return null;
    }

    @Override
    public Map<String, Object> findBooksByPublishinghouseContaining(String publishingHouse) {
        return null;
    }

    @Override
    public Map<String, Object> findBooksByIsbnContaing(String isbn) {
        return null;
    }
}
