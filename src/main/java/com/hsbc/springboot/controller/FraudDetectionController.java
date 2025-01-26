package com.hsbc.springboot.controller;

import com.hsbc.springboot.core.FraudDetectionRuleParser;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.core.KafkaProducerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudDetectionController
 * @package: com.hsbc.springboot.controller
 * @author: bruce
 * @date: 2025/1/24 19:51
 */
@RequestMapping("/parser")
@RestController
public class FraudDetectionController {
    @Autowired
    private FraudDetectionRuleParser ruleService;

    @Autowired
    private KafkaProducerSender kafkaProducerService;

    @PostMapping("/detectFraud")
    public boolean detectFraud(@RequestBody Transaction transaction) {
        // 定义一个简单的 SPEL 规则：交易金额大于 1000 则可能是欺诈
        String ruleExpression = "amount > 1000";
        return ruleService.evaluateRule(transaction, ruleExpression);
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent successfully";
    }
}
