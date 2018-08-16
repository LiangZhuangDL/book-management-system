package com.example.bookmanagementsystem.enums;

public enum BookCoverEnum {

    DEFAULTCOVER("");

    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    BookCoverEnum() {
    }

    BookCoverEnum(String cover) {
        this.cover = cover;
    }
}
