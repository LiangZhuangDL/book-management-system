package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;

/**
 * @program: book-management-system
 * @description: 书架实体类
 * @author: Simon Zhuang
 * @create: 2018-08-14 15:36
 **/
@Entity
@Table(name = "book_shelf")
public class BookShelf extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String shelfNumber;

    @Column
    private Integer horizontal;

    @Column
    private Integer vertical;

    @Column
    private String room;

    @Column
    private String floor;

    @Column
    private Boolean isDelete = false;

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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public BookShelf() {
    }

    public BookShelf(String shelfNumber, Integer horizontal, Integer vertical, String room, String floor) {
        this.shelfNumber = shelfNumber;
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.room = room;
        this.floor = floor;
    }
}
