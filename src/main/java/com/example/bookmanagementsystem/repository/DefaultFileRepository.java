package com.example.bookmanagementsystem.repository;

import com.example.bookmanagementsystem.entity.DefaultFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: book-management-system
 * @description: 文件JPA接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface DefaultFileRepository extends MongoRepository<DefaultFile, String> {

    DefaultFile findDefaultFileById(String id);
}
