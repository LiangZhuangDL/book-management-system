package com.example.bookmanagementsystem.entity.book;

import com.example.bookmanagementsystem.entity.BasicEntity;

import javax.persistence.*;

/**
 * @program: book-management-system
 * @description: 借书信息实体类
 * @author: Simon Zhuang
 * @create: 2018-08-14 20:29
 **/

@Entity
@Table(name = "borrowed_book")
public class BorrowedBook extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
