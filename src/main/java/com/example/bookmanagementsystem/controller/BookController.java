package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.BookSearchDTO;
import com.example.bookmanagementsystem.service.BookService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: book-management-system
 * @description: 图书控制器
 * @author: Simon Zhuang
 * @create: 2018-08-15 11:40
 **/

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/search")
    public Response search(BookSearchDTO bookSearchDTO){
        /** 
        * @Description: 根据查询索引查询图书基本信息 
        * @Param: [bookSearchDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/16 
        **/ 
        Integer type = bookSearchDTO.getType();
        Response response = new Response();
        Map<String, Object> map;
        if(type.equals(0)){
            map = bookService.findBooksByTitleContaining(bookSearchDTO.getText());
        }else if(type.equals(1)){
            map = bookService.findBooksByAuthorContaining(bookSearchDTO.getText());
        }else if(type.equals(2)){
            map = bookService.findBooksByPublishingHouseContaining(bookSearchDTO.getText());
        }else if(type.equals(3)){
            map = bookService.findBooksByIsbnContaining(bookSearchDTO.getText());
        } else {
            return response.failure();
        }
        return response.success(map);
    }

}
