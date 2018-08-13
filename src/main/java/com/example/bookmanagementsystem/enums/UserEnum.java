package com.example.bookmanagementsystem.enums;

/**
 * @program: book-management-system
 * @description: 用户枚举类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public enum UserEnum {
    ANONYMOUSUSER("游客");

    private String text;

    UserEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
