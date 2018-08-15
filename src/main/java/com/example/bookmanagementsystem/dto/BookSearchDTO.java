package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 图书搜索DTO
 * @author: Simon Zhuang
 * @create: 2018-08-15 14:54
 **/
public class BookSearchDTO{

    private Integer type;

    private String text;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BookSearchDTO(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    public BookSearchDTO() {
    }
}
