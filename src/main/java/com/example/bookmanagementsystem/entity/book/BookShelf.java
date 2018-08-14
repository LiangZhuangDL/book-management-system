package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: book-management-system
 * @description: 书架实体类
 * @author: Simon Zhuang
 * @create: 2018-08-14 15:36
 **/
@Entity
@Table(name = "book_shelf")
public class BookShelf extends BasicEntity {
}
