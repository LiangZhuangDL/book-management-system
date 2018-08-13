package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.entity.book.DefaultFile;
import com.example.bookmanagementsystem.service.DefaultFileService;
import com.example.bookmanagementsystem.utils.Response;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: book-management-system
 * @description: 文件控制器
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private DefaultFileService defaultFileService;

    @PostMapping(value = "/upload")
    public Response defaultUpload(@RequestParam("file") MultipartFile file){
        DefaultFile returnFile = null;
        Response response = new Response();
        try{
            DefaultFile f = new DefaultFile(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Binary(file.getBytes()));
            returnFile = defaultFileService.save(f);
            if(!ObjectUtils.isEmpty(returnFile)){
                Map<String, Object> map = new HashMap<>();
                map.put("result", "上传成功");
                return response.success(map);
            }else{
                return response.failure();
            }
        }catch (IOException e){
            e.printStackTrace();
            return response.failure();
        }
    }
}
