package com.example.bookmanagementsystem.utils;

public class Meta {

    private Boolean success;

    private String message;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Meta(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Meta() {
    }
}
