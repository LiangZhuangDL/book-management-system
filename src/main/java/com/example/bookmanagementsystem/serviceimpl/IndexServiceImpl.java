package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.IndexService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public Map<String, Object> getCurrentUserInfo() {
        Map<String, Object> map = new HashMap<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Integer tag = 0;
//        for(GrantedAuthority authority: authorities){
//            if(authority.getAuthority().equals("ADMIN")){
//                tag = 1;
//            }
//        }
//        if(tag.equals(1)){
//            map.put("authority", "ADMIN");
//        }else {
//            map.put("authority", "USER");
//        }
        map.put("authorities", authorities);
        map.put("username", username);
        return map;
    }
}
