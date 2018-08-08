package com.example.bookmanagementsystem.service;

public interface MailService {
    Boolean sendHtmlMail(String to, String subject, String content);

    String buildContent(String activeCode);
}
