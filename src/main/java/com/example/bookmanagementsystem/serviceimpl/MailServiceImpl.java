package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @program: book-management-system
 * @description: 邮件业务实现类
 * @author: Simon Zhuang
 * @create: 2018-08-13 11:51
 **/
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Boolean sendHtmlMail(String to, String subject, String content){
        /** 
        * @Description: 发送激活邮件 
        * @Param: [to, subject, content] 
        * @return: java.lang.Boolean 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("liang_zhuang@foxmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);

            return true;
        }catch (MessagingException me){
            me.printStackTrace();
            return false;
        }
    }

    @Override
    public String buildContent(String activeCode) {
        /** 
        * @Description: 生成激活码 
        * @Param: [activeCode] 
        * @return: java.lang.String 
        * @Author: Simon Zhuang
        * @Date: 2018/8/13 
        **/ 
        String content = "<html>\n<body>\n" +
                "<h3>请复制您的激活码去激活您的账号</h3>\n" +
                "<br>\n" +
                "localhost:4000/active/active-code?activeCode=" + activeCode + "\n" +
                "</body>\n</html>\n";
        return content;
    }
}
