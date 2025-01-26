package com.hsbc.springboot.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaProducerSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmailSender emailSender;

    private static final String TOPIC = "test-topic";

    @Async("asyncfraudDetectionExecutor")
    public void sendMessage(String message) {
        log.info(message);
        kafkaTemplate.send(TOPIC, message);
        String subject = "违规交易风险告知邮件！";
        emailSender.sendSimpleEmail(subject,message);
    }
}