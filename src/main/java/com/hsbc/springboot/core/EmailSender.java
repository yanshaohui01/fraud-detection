package com.hsbc.springboot.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 〈功能概述〉<br>
 *
 * @className: EmailService
 * @package: com.hsbc.springboot.core
 * @author: bruce
 * @date: 2025/1/24 21:10
 */
@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Value("hsbc.email.from")
    private String eMailFrom;

    @Value("hsbc.email.to")
    private String eMailTo;

    public void sendSimpleEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(eMailTo);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("yan_shaohui@126.com");
        mailSender.send(message);
    }
}
