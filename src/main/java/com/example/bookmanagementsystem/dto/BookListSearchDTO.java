package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 批量图书搜索DTO
 * @author: Simon Zhuang
 * @create: 2018-08-17 10:47
 **/
public class BookListSearchDTO {

    private String searchType;

    private String text;

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BookListSearchDTO() {
    }

    public BookListSearchDTO(String searchType, String text) {
        this.searchType = searchType;
        this.text = text;
    }
}
