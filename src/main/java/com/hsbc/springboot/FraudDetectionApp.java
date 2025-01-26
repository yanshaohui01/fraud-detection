package com.hsbc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Hello world!
 */
@SpringBootApplication
@MapperScan("com.hsbc.springboot.mapper")
public class FraudDetectionApp {

    public static void main(String[] args) {
        SpringApplication.run(FraudDetectionApp.class, args);
    }
}
