package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.book.DefaultFile;
import com.example.bookmanagementsystem.repository.DefaultFileRepository;
import com.example.bookmanagementsystem.service.DefaultFileService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> uploadAvatar(MultipartFile file) {
        /**
        * @Description: 上传头像，返回头像ID和图片的Base64码
        * @Param: [file]
        * @return: java.util.Map<java.lang.String,java.lang.Object>
        * @Author: Simon Zhuang
        * @Date: 2018/8/14
        **/
        Map<String, Object> map = new HashMap<>();
        try{
            DefaultFile f = new DefaultFile(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Binary(file.getBytes()));
            DefaultFile returnFile = save(f);
            if(!ObjectUtils.isEmpty(returnFile)){
                String avatar = returnFile.getId();
                Base64.Encoder encoder = Base64.getEncoder();
                String text = encoder.encodeToString(returnFile.getContent().getData()).replaceAll("\n","");
                String image = "data:image/jpeg;base64," + text;
                map.put("avatar", avatar);
                map.put("image", image);
                map.put("success", true);
                return map;
            }else{
                map.put("success", false);
                return map;
            }
        }catch (IOException e){
            e.printStackTrace();
            map.put("success", false);
            return map;
        }
    }
}
