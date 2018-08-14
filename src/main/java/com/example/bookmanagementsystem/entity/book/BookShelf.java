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
    private String number;

    @Column
    private Integer row;

    @Column
    private Integer column;

    @Column
    private String room;

    @Column
    private String floor;

    @Column
    private Boolean isDelete = false;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
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

    public BookShelf(String number, Integer row, Integer column, String room, String floor) {
        this.number = number;
        this.row = row;
        this.column = column;
        this.room = room;
        this.floor = floor;
    }

    public BookShelf() {
    }
}
