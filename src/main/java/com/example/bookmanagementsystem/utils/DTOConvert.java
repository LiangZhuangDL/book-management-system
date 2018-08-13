package com.example.bookmanagementsystem.utils;

/**
 * @program: book-management-system
 * @description: DTO转Bean接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface DTOConvert<T, S> {

    T convert(S s);
}
