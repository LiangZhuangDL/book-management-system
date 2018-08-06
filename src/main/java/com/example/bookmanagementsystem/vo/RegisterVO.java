package com.example.bookmanagementsystem.vo;

public class RegisterVO {

    private String pageName = "register";

    private Boolean isLogin;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public RegisterVO() {
    }

    public RegisterVO(String pageName, Boolean isLogin) {
        this.pageName = pageName;
        this.isLogin = isLogin;
    }

    public RegisterVO(String pageName, Boolean isLogin, String message) {
        this.pageName = pageName;
        this.isLogin = isLogin;
        this.message = message;
    }

    public RegisterVO(Boolean isLogin) {
        this.isLogin = isLogin;
    }
}
