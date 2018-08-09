package com.example.bookmanagementsystem.serviceimpl;

import com.example.bookmanagementsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Boolean sendHtmlMail(String to, String subject, String content){
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
        String content = "<html>\n<body>\n" +
                "<h3>请复制您的激活码去激活您的账号</h3>\n" +
                "<br>\n" +
                "localhost:4000/active/active-code?activeCode=" + activeCode + "\n" +
                "</body>\n</html>\n";
        return content;
    }
}
