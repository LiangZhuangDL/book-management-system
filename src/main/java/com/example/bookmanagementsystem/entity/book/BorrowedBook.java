package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;

import javax.persistence.*;
import java.util.List;

/**
 * @program: book-management-system
 * @description: 借书信息实体类
 * @author: Simon Zhuang
 * @create: 2018-08-14 20:29
 **/

@Entity
@Table(name = "borrowed_book")
public class BorrowedBook extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer maxBorrowedQuantity = 6;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "borrowed_user", joinColumns = @JoinColumn(name = "basic_book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "basic_user_id", referencedColumnName = "id"))
    private BasicUser basicUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "borrowed_book_book", joinColumns = @JoinColumn(name = "borrowed_book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;

    @Column
    private Boolean isFinished = false;

    public Integer getMaxBorrowedQuantity() {
        return maxBorrowedQuantity;
    }

    public void setMaxBorowedQuantity(Integer maxBorrowedQuantity) {
        this.maxBorrowedQuantity = maxBorrowedQuantity;
    }

    public BasicUser getBasicUser() {
        return basicUser;
    }

    public void setBasicUser(BasicUser basicUser) {
        this.basicUser = basicUser;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public BorrowedBook() {
    }

    public BorrowedBook(Integer maxBorrowedQuantity, BasicUser basicUser, List<Book> books) {
        this.maxBorrowedQuantity = maxBorrowedQuantity;
        this.basicUser = basicUser;
        this.books = books;
    }
}
