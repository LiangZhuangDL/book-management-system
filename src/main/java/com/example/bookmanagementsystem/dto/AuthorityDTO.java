package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.entity.authentication.Authority;
import com.example.bookmanagementsystem.utils.DTOConvert;
import org.springframework.beans.BeanUtils;

/**
 * @program: book-management-system
 * @description: 角色DTO类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class AuthorityDTO implements DTOConvert<Authority, AuthorityDTO> {
    private Long userId;

    private String authorityName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public AuthorityDTO() {
    }

    public AuthorityDTO(Long userId, String authorityName) {
        this.userId = userId;
        this.authorityName = authorityName;
    }

    @Override
    public Authority convert(AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        BeanUtils.copyProperties(authorityDTO,authority);
        return authority;
    }
}
