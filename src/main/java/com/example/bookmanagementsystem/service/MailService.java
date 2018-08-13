package com.example.bookmanagementsystem.service;

/**
 * @program: book-management-system
 * @description: 邮件业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface MailService {
    Boolean sendHtmlMail(String to, String subject, String content);

    String buildContent(String activeCode);
}
