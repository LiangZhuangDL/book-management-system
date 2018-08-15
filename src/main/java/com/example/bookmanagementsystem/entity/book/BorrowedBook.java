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
    private Integer maxBorowedQuantity = 6;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "borrowed_user", joinColumns = @JoinColumn(name = "basic_book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "basic_user_id", referencedColumnName = "id"))
    private BasicUser basicUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "borrowed_book_book", joinColumns = @JoinColumn(name = "borrowed_book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> books;

    @Column
    private Boolean isFinished = false;

    public Integer getMaxBorowedQuantity() {
        return maxBorowedQuantity;
    }

    public void setMaxBorowedQuantity(Integer maxBorowedQuantity) {
        this.maxBorowedQuantity = maxBorowedQuantity;
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

    public BorrowedBook(Integer maxBorowedQuantity, BasicUser basicUser, List<Book> books) {
        this.maxBorowedQuantity = maxBorowedQuantity;
        this.basicUser = basicUser;
        this.books = books;
    }
}
