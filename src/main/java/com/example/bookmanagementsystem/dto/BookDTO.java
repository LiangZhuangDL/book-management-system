package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.entity.book.Book;
import com.example.bookmanagementsystem.utils.DTOConvert;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class BookDTO implements DTOConvert<Book, BookDTO> {

    private String cover;

    private String title;

    private String author;

    private String isbn;

    private String publishingHouse;

    private Date publishedDate;

    private Integer edition;

    private Double price;

    private Integer quantity;

    private String number;

    private String shelfNumber;

    private Integer horizontal;

    private Integer vertical;

    private String room;

    private String floor;

    private String sign;

    private String text;

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

    public String getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Integer horizontal) {
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public void setVertical(Integer vertical) {
        this.vertical = vertical;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BookDTO() {
    }

    public BookDTO(String cover, String title, String author, String isbn, String publishingHouse, Date publishedDate, Integer edition, Double price, Integer quantity, String number, String shelfNumber, Integer horizontal, Integer vertical, String room, String floor, String sign, String text) {
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
        this.shelfNumber = shelfNumber;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.room = room;
        this.floor = floor;
        this.sign = sign;
        this.text = text;
    }

    @Override
    public Book convert(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }
}
