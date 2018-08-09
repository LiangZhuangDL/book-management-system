package com.example.bookmanagementsystem.dto;

public class AuthorityDTO {
    private Long userId;

    private String AuthorityName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthorityName() {
        return AuthorityName;
    }

    public void setAuthorityName(String authorityName) {
        AuthorityName = authorityName;
    }

    public AuthorityDTO() {
    }

    public AuthorityDTO(Long userId, String authorityName) {
        this.userId = userId;
        AuthorityName = authorityName;
    }
}
