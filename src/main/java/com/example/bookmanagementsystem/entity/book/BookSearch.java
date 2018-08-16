package com.example.bookmanagementsystem.entity.book;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @program: book-management-system
 * @description: 图书搜索实体类
 * @author: Simon Zhuang
 * @create: 2018-08-15 11:45
 **/

@Document(indexName = "book", type = "book")
public class BookSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String title;

    private String author;

    private String publishingHouse;

    private String isbn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public BookSearch(String id, String title, String author, String publishingHouse, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.isbn = isbn;
    }

    public BookSearch() {
    }
}
