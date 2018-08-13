package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.book.DefaultFile;
import com.example.bookmanagementsystem.repository.DefaultFileRepository;
import com.example.bookmanagementsystem.service.DefaultFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: book-management-system
 * @description: 文件业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class DefaultFileServiceImpl implements DefaultFileService {

    @Autowired
    private DefaultFileRepository defaultFileRepository;

    @Override
    public DefaultFile save(DefaultFile defaultFile) {
        /**
        * @Description: 保存文件到MongoDB文件数据库
        * @Param: [defaultFile]
        * @return: com.example.bookmanagementsystem.entity.book.DefaultFile
        * @Author: Simon Zhuang
        * @Date: 2018/8/13
        **/
        return defaultFileRepository.save(defaultFile);
    }
}
