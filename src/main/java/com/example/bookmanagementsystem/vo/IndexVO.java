package com.example.bookmanagementsystem.vo;

public class IndexVO {

    private String username;

    private String authorityName;

    private String pageName = "index";

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

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public IndexVO(String username) {
        this.username = username;
    }

    public IndexVO(String username, String authorityName) {
        this.username = username;
        this.authorityName = authorityName;
    }

    public IndexVO(String username, String authorityName, String pageName) {
        this.username = username;
        this.authorityName = authorityName;
        this.pageName = pageName;
    }

    public IndexVO() {
    }
}
