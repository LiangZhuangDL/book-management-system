package com.example.bookmanagementsystem.enums;

public enum UserEnum {
    ANONYMOUSUSER("游客");

    private String text;

    UserEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
