package com.example.bookmanagementsystem.vo;

public class IndexVO {

    private String username;

    private String authorityName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public IndexVO(String username) {
        this.username = username;
    }

    public IndexVO(String username, String authorityName) {
        this.username = username;
        this.authorityName = authorityName;
    }

    public IndexVO() {
    }
}
