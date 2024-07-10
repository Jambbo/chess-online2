package com.kukuxer.registration.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Async
    public void sendMailTo(String to,String body){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromMail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Your recovery password page");
            mimeMessageHelper.setText(
                    "<h1>Recover your password<h1>" +
                            "<a href=\"" + body + "\" " +
                            "style=\"display: inline-block; padding: 10px 20px; " +
                            "background-color: #007bff; color: #fff; " +
                            "text-decoration: none; border-radius: 5px;\">Recover</a>"
                    ,true);
            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    }

