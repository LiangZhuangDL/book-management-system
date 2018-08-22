package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;
import com.example.bookmanagementsystem.entity.authentication.BasicUser;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: book-management-system
 * @description: 借书历史记录实体类
 * @author: Simon Zhuang
 * @create: 2018-08-22 11:10
 **/
@Entity
@Table(name = "borrowed_book_history")
public class BorrowedBookHistory extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Date finishDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "history_user", joinColumns = @JoinColumn(name = "history_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "basic_user_id", referencedColumnName = "id"))
    private BasicUser basicUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "history_book", joinColumns = @JoinColumn(name = "history_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Book book;

    @Column
    private Boolean isFinished = false;

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public BasicUser getBasicUser() {
        return basicUser;
    }

    public void setBasicUser(BasicUser basicUser) {
        this.basicUser = basicUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public BorrowedBookHistory(Date finishDate, BasicUser basicUser, Book book) {
        this.finishDate = finishDate;
        this.basicUser = basicUser;
        this.book = book;
    }

    public BorrowedBookHistory(BasicUser basicUser, Book book) {
        this.basicUser = basicUser;
        this.book = book;
    }
}
