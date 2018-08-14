package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.entity.file.DefaultFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @program: book-management-system
 * @description: 文件业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface DefaultFileService {

    DefaultFile save(DefaultFile defaultFile);

    Map<String, Object> uploadAvatar(MultipartFile file);

    String findAvatarBase64ById(String id);
}
