package com.example.bookmanagementsystem.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @program: book-management-system
 * @description: 邮件业务接口
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
public interface MailService {

    @Transactional
    Boolean sendHtmlMail(String to, String subject, String content);

    @Transactional
    String buildContent(String activeCode);
}
