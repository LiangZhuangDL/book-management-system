package com.example.bookmanagementsystem.enums;

public enum UserTypeEnum {
    ADMIN("ADMIN"),USER("USER");

    private String text;

    UserTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
