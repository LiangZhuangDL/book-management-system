package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;

/**
 * @program: book-management-system
 * @description: 中国图书馆图书基本大类
 * @author: Simon Zhuang
 * @create: 2018-08-14 15:20
 **/
@Entity
@Table(name = "book_basic_type")
public class BookBasicType extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sign;

    @Column
    private String text;

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

    public BookBasicType() {
    }

    public BookBasicType(String sign, String text) {
        this.sign = sign;
        this.text = text;
    }
}
