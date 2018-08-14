package com.example.bookmanagementsystem.enums;

/**
 * @program: book-management-system
 * @description: 默认头像枚举类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public enum DefaultAvatarEnum {
    MALE(""),FEMAILE(""),UNKNOW("");
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatarId(String avatar) {
        this.avatar = avatar;
    }

    DefaultAvatarEnum(String avatar) {
        this.avatar = avatar;
    }
}
