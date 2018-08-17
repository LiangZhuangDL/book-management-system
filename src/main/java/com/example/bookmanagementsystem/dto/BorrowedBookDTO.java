package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 借书DTO
 * @author: Simon Zhuang
 * @create: 2018-08-17 16:04
 **/
public class BorrowedBookDTO {

    private String title;

    private String author;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BorrowedBookDTO(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public BorrowedBookDTO() {
    }

    @Override
    public String toString() {
        return "BorrowedBookDTO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
