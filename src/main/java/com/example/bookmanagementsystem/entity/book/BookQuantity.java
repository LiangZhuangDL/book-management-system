package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;

/**
 * @program: book-management-system
 * @description: 图书数量表
 * @author: Simon Zhuang
 * @create: 2018-08-16 09:25
 **/
@Entity
@Table(name = "book_quantity")
public class BookQuantity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer leftQuantities;

    @Column
    private Boolean isDelete;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_quantity_book", joinColumns = @JoinColumn(name = "book_quantity_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Book book;

    public Integer getLeftQuantities() {
        return leftQuantities;
    }

    public void setLeftQuantities(Integer leftQuantities) {
        this.leftQuantities = leftQuantities;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookQuantity() {
    }

    public BookQuantity(Integer leftQuantities, Book book) {
        this.leftQuantities = leftQuantities;
        this.book = book;
    }
}
