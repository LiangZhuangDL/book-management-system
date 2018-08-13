package com.example.bookmanagementsystem.dto;

/**
 * @program: book-management-system
 * @description: 角色DTO
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public class AuthorityDTO {
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
}
