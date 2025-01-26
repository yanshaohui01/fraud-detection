package com.hsbc.springboot;


import com.hsbc.springboot.core.FraudDetectionRuleParser;
import com.hsbc.springboot.core.RuleParser;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.service.FraudDetectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @Autowired
    private FraudDetectionRuleParser fraudDetectionRuleParser;

    @Autowired
    private RuleParser ruleParser;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test-topic";

    @Test
    public void shouldAnswerWithTrue() {
        fraudDetectionService.analyzeTransaction(null);
        assertTrue(true);
    }

    @Test
    public void ruleParserTest() {
       Transaction transaction = new Transaction();
       transaction.setAmount(new BigDecimal(10000));
       transaction.setCountry("US");
       transaction.setId(1);
       transaction.setStatus("1");
       transaction.setTimestamp(new Date());
       transaction.setTransactionType("转账");
       transaction.setUserId(1);
       String ruleExpression = "amount > 1000";
       ruleParser.processTransaction(transaction);
    }

    @Test
    public  void kafkaTest() {
        String message = "this is a test message, from developer to test-topic, 测试汉语编码";
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Produced message: " + message);

    }
}
