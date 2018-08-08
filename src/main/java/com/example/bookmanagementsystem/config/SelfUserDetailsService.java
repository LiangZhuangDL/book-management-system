package com.example.bookmanagementsystem.config;

import com.example.bookmanagementsystem.entity.authentication.BasicUser;
import com.example.bookmanagementsystem.repository.BasicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private BasicUserRepository basicUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BasicUser basicUser = basicUserRepository.findBasicUserByUsername(username);
        if(ObjectUtils.isEmpty(basicUser)){
            return new BasicUser();
        }else {
            basicUser.setEnabled(true);
            return basicUser;
        }
    }
}
