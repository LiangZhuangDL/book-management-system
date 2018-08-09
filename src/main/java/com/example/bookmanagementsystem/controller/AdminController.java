package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.dto.AuthorityDTO;
import com.example.bookmanagementsystem.entity.DefaultFile;
import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.repository.AuthorityRepository;
import com.example.bookmanagementsystem.service.AuthorityService;
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

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DefaultFileService defaultFileService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityService authorityService;

    @PostMapping(value = "/upload")
    public Response defaultUpload(@RequestParam("file")MultipartFile file){
        DefaultFile returnFile = null;
        Response response = new Response();
        try{
            DefaultFile f = new DefaultFile(file.getOriginalFilename(), file.getContentType(), file.getSize(), new Binary(file.getBytes()));
            returnFile = defaultFileService.save(f);
            if(!ObjectUtils.isEmpty(returnFile)){
                Map<String, Object> map = new HashMap<>();
                map.put("result", "upload success");
                return response.success(map);
            }else{
                return response.failure();
            }
        }catch (IOException e){
            e.printStackTrace();
            return response.failure();
        }
    }

    @PostMapping(value = "/createAuthority")
    public Response createAuthority(@RequestParam("authorityName")String authorityName){
        Authority result = authorityRepository.findAuthorityByName(authorityName);
        Response response = new Response();
        if(ObjectUtils.isEmpty(result)){
            Authority authority = new Authority(authorityName);
            Boolean tag = authorityService.save(authority);
            if(tag){
                Map<String, Object> map = new HashMap<>();
                map.put("result", "创建角色成功");
                return response.success(map);
            }else{
                return response.failure();
            }
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/addAuthority")
    public Response addAuthority(AuthorityDTO authorityDTO){
        Boolean tag = authorityService.addAuthority(authorityDTO);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "添加角色成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/removeAuthority")
    public Response removeAuthority(AuthorityDTO authorityDTO){
        Boolean tag = authorityService.removeAuthority(authorityDTO);
        Response response = new Response();
        if(tag){
            Map<String, Object> map = new HashMap<>();
            map.put("result", "删除角色成功");
            return response.success(map);
        }else{
            return response.failure();
        }
    }

    @PostMapping(value = "/removeUser")
    public Response removeUser(Long userId){
        return null;
    }
}
