package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: book-management-system
 * @description: 图书实体类
 * @author: Simon Zhuang
 * @create: 2018-08-14 14:37
 **/
@Entity
@Table(name = "book")
public class Book extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cover;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String isbn;

    @Column
    private String publishingHouse;

    @Column
    private Date publishedDate;

    @Column
    private Integer edition; //版次

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @Column
    private String number; //编号

    @Column
    private Boolean isRenew = false; //是否续借

    @Column
    private Boolean isDelete = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_book_basic_type", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_basic_type_id", referencedColumnName = "id"))
    private BookBasicType bookBasicType;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_bookshelf", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_shelf_id", referencedColumnName = "id"))
    private BookShelf bookShelf;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BookBasicType getBookBasicType() {
        return bookBasicType;
    }

    public void setBookBasicType(BookBasicType bookBasicType) {
        this.bookBasicType = bookBasicType;
    }

    public BookShelf getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    public Boolean getRenew() {
        return isRenew;
    }

    public void setRenew(Boolean renew) {
        isRenew = renew;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Book() {
    }

    public Book(String cover, String title, String author, String isbn, String publishingHouse, Date publishedDate, Integer edition, Double price, Integer quantity, String number, BookBasicType bookBasicType, BookShelf bookShelf) {
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishingHouse = publishingHouse;
        this.publishedDate = publishedDate;
        this.edition = edition;
        this.price = price;
        this.quantity = quantity;
        this.number = number;
        this.bookBasicType = bookBasicType;
        this.bookShelf = bookShelf;
    }
}
