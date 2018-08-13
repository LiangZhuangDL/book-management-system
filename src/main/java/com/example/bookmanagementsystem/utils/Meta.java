package com.example.bookmanagementsystem.utils;

/**
 * @program: book-management-system
 * @description: 数据源实体类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class Meta {

    private Boolean success;

    private String message;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Meta(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Meta() {
    }
}
