package com.example.bookmanagementsystem.utils;

/**
 * @program: book-management-system
 * @description: 响应实体类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class Response {

    private static final String OK = "ok";

    private static final String ERROR = "error";

    private Meta meta;

    private Object data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    public Response() {
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }
}
