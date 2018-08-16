package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 单个图书查询DTO
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class SingleBookSearchDTO {

    private String title;

    private String author;

    private String publishingHouse;

    private String isbn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public SingleBookSearchDTO(String title, String author, String publishingHouse, String isbn) {
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.isbn = isbn;
    }

    public SingleBookSearchDTO() {
    }
}
