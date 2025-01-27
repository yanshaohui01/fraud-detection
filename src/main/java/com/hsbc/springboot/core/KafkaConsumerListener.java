package com.hsbc.springboot.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * 〈功能概述〉<br>
 *
 * @className: KafkaConsumerListener
 * @package: com.hsbc.springboot.core
 * @author: bruce
 * @date: 2025/1/25 9:06
 */
@Slf4j
@Service
public class KafkaConsumerListener {
    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void listen(String message) {
        log.warn("transaction exception message: {}",message);
    }
}
