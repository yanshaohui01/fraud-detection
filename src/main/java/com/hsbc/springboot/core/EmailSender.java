package com.hsbc.springboot.core;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("yan_shaohui@126.com");
        mailSender.send(message);
    }
}
