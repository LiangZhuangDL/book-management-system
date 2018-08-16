package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.entity.file.DefaultFile;
import com.example.bookmanagementsystem.entity.user.UserDetails;
import com.example.bookmanagementsystem.enums.BookCoverEnum;
import com.example.bookmanagementsystem.enums.DefaultAvatarEnum;
import com.example.bookmanagementsystem.repository.anthentication.BasicUserRepository;
import com.example.bookmanagementsystem.repository.file.DefaultFileRepository;
import com.example.bookmanagementsystem.repository.user.UserDetailsRepository;
import com.example.bookmanagementsystem.service.DefaultFileService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public DefaultFile save(DefaultFile defaultFile) {
        /**
        * @Description: 保存文件到MongoDB文件数据库
        * @Param: [defaultFile]
        * @return: com.example.bookmanagementsystem.entity.file.DefaultFile
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

    @Override
    public Map<String, Object> uploadCover(MultipartFile file) {
        /**
        * @Description: 上传图书封面，返回封面ID和图片的Base64码
        * @Param: [file]
        * @return: java.util.Map<java.lang.String,java.lang.Object>
        * @Author: Simon Zhuang
        * @Date: 2018/8/16
        **/
        Map<String, Object> map = new HashMap<>();
        try{
            DefaultFile f = new DefaultFile(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Binary(file.getBytes()));
            DefaultFile returnFile = save(f);
            if(!ObjectUtils.isEmpty(returnFile)){
                String cover = returnFile.getId();
                Base64.Encoder encoder = Base64.getEncoder();
                String text = encoder.encodeToString(returnFile.getContent().getData()).replaceAll("\n","");
                String image = "data:image/jpeg;base64," + text;
                map.put("cover", cover);
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

    @Override
    public String findAvatarBase64ById(String id) {
        /**
        * @Description: 通过文件ID返回头像图片的Base64码，根据用户性别返回头像
        * @Param: [id]
        * @return: java.lang.String
        * @Author: Simon Zhuang
        * @Date: 2018/8/14
        **/
        DefaultFile returnFile = defaultFileRepository.findDefaultFileById(id);
        if(ObjectUtils.isEmpty(returnFile)){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
            if(!ObjectUtils.isEmpty(basicUser)){
                UserDetails userDetails = userDetailsRepository.findUserDetailsByBasicUser(basicUser);
                if(!ObjectUtils.isEmpty(userDetails)){
                    Boolean sex = userDetails.getSex();
                    if(sex){
                        return DefaultAvatarEnum.MALE.getAvatar();
                    }else {
                        return DefaultAvatarEnum.FEMAILE.getAvatar();
                    }
                }else{
                    return DefaultAvatarEnum.UNKNOW.getAvatar();
                }
            }else {
                return DefaultAvatarEnum.UNKNOW.getAvatar();
            }
        }else{
            Base64.Encoder encoder = Base64.getEncoder();
            String text = encoder.encodeToString(returnFile.getContent().getData()).replaceAll("\n","");
            return "data:image/jpeg;base64," + text;
        }
    }

    @Override
    public String findCoverBase64ById(String id) {
        /**
        * @Description:  根据图书封面ID返回封面图片的Base64码
        * @Param: [id]
        * @return: java.lang.String
        * @Author: Simon Zhuang
        * @Date: 2018/8/16
        **/
        DefaultFile returnFile = defaultFileRepository.findDefaultFileById(id);
        if(!ObjectUtils.isEmpty(returnFile)){
            Base64.Encoder encoder = Base64.getEncoder();
            String text = encoder.encodeToString(returnFile.getContent().getData()).replaceAll("\n","");
            return "data:image/jpeg;base64," + text;
        }else{
            return BookCoverEnum.DEFAULTCOVER.getCover();
        }
    }
}
