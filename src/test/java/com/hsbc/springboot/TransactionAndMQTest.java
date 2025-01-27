package com.hsbc.springboot;


import com.hsbc.springboot.core.EmailSender;
import com.hsbc.springboot.core.FraudDetectionRuleParser;
import com.hsbc.springboot.core.RuleParser;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.service.FraudDetectionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 交易和MQ队列及email发送单元测试
 * Unit test for FraudDetectionApp.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionAndMQTest {

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @Autowired
    private FraudDetectionRuleParser fraudDetectionRuleParser;

    @Autowired
    private RuleParser ruleParser;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmailSender emailSender;


    private static final String TOPIC = "test-topic";


    @Test
    public void shouldAnswerWithTrue() {
        fraudDetectionService.analyzeTransaction(null);
        Assertions.assertTrue(true);
    }

    /**
     * 规则解析测试用例
     */
    @Test
    public void ruleParserTest() {
       Transaction transaction = new Transaction();
       transaction.setAmount(new BigDecimal(50000));
       transaction.setCountry("US");
       transaction.setId(1);
       transaction.setStatus("1");
       transaction.setTimestamp(new Date());
       transaction.setTransactionType("转账");
       boolean result = ruleParser.processTransaction(transaction);
        assertEquals(true,result);
    }


    /**
     * 发送MQ消息测试用例
     */
    @Test
    public  void kafkaTest() {
        String message = "this is a test message, from developer to test-topic, 测试汉语编码";
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Produced message: " + message);
        assertEquals(true,true);
    }

    /**
     * 发送邮件测试用例
     */
    @Test
    public  void emailTest() {
        String message = "this is a test message, from developer to test-topic, 测试汉语编码";
        emailSender.sendSimpleEmail("this is subject","this is text!");
        assertEquals(true,true);
    }

}
