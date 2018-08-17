package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.*;
import com.example.bookmanagementsystem.service.BookService;
import com.example.bookmanagementsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping(value = "/bookInformation")
    public Response getBookInformation(SingleBookSearchDTO singleBookSearchDTO){
        /**
        * @Description: 根据BookSearch对象返回的信息返回Book对象（搜索单条图书详细信息）
        * @Param: [singleBookSearchDTO]
        * @return: com.example.bookmanagementsystem.utils.Response
        * @Author: Simon Zhuang
        * @Date: 2018/8/17
        **/
        Map<String, Object> map = bookService.getBookByBookSearch(singleBookSearchDTO);
        Boolean tag = (Boolean)map.get("success");
        Response response = new Response();
        if(tag){
            return response.success(map);
        }else {
            return response.failure();
        }
    }

    @GetMapping(value = "/bookList/{page}/{size}")
    public Response bookList(@PathVariable("page")Integer page, @PathVariable("size")Integer size, BookListSearchDTO bookListSearchDTO){
        /** 
        * @Description: 分页查询，按照类型查询图书批量信息
        * @Param: [page, size, bookListSearchDTO] 
        * @return: com.example.bookmanagementsystem.utils.Response 
        * @Author: Simon Zhuang
        * @Date: 2018/8/17 
        **/ 
        Map<String, Object> map = bookService.getBookListSearch(page, size, bookListSearchDTO);
        Boolean tag = (Boolean)map.get("success");
        Response response = new Response();
        if(tag){
            return response.success(map);
        }else {
            return response.failure();
        }
    }

    @PostMapping(value = "/borrow")
    public Response borrowBook(@RequestBody BorrowedBookListDTO borrowedBooksDTOS){
        List<BorrowedBookDTO> borrowedBookDTOS = borrowedBooksDTOS.getBorrowedBookDTOS();
        for(BorrowedBookDTO borrowedBookDTO: borrowedBookDTOS){
            System.out.println(borrowedBookDTO);
            System.out.println("*****");
        }
        return null;
    }

}
