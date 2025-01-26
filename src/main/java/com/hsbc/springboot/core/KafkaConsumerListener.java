package com.hsbc.springboot.core;

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
@Service
public class KafkaConsumerListener {
    @KafkaListener(topics = "test-topic", groupId = "my-group", properties={"bootstrap.servers=122.9.206.65:9092"})
    public void listen(String message) {
        System.out.println("Consumed message: " + message);
    }
}
