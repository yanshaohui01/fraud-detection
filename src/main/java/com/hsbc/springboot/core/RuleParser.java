package com.hsbc.springboot.core;

import cn.hutool.core.collection.CollectionUtil;
import com.hsbc.springboot.config.GlobalVariable;
import com.hsbc.springboot.entity.FraudTransaction;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.mapper.FraudTransactionMapper;
import com.hsbc.springboot.service.FraudDetectionService;
import com.hsbc.springboot.service.RuleService;
import com.hsbc.springboot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 解析规则，分配至不同的过滤器
 *
 * @className: RuleParser
 * @package: com.hsbc.springboot.core
 * @author: bruce
 * @date: 2025/1/24 16:36
 */
@Slf4j
@Service
public class RuleParser {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private FraudTransactionMapper fraudTransactionMapper;

    @Autowired
    private KafkaProducerSender kafkaProducerSender;

    // 从数据库加载规则校验数据
    private List<String> loadRulesFromDB() {
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        Map<String,Object> ruleMap = hashOps.entries(GlobalVariable.RULE_HASH);
        if(CollectionUtil.isEmpty(ruleMap)){
            List<Rule> rules = ruleService.getAllRule();
            for(Rule rule:rules){
                hashOps.put(GlobalVariable.RULE_HASH,rule.getRuleName(),rule.getRuleDescription());
            }
        }
        return  new ArrayList<>(ruleMap.keySet());
    }

    public boolean evaluateRule(Transaction transaction, String ruleExpression) {
        // 初始化表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 配置上下文并设置变量
        StandardEvaluationContext context = new StandardEvaluationContext(transaction);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        // 解析表达式并求值
        Expression expression = parser.parseExpression(ruleExpression);
        return expression.getValue(context, Boolean.class);
    }

    public boolean processTransaction(Transaction transaction) {
        List<String> rules = loadRulesFromDB();
        boolean[] fraud = new boolean[rules.size()];
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String rule : rules) {
            boolean isFraud = evaluateRule(transaction, rule);
            fraud[index++] = isFraud;
            if (isFraud) {
                String notice = String.format(" 交易：%d, 账号：%s, 金额: %s  存在违规行为：%s, 请确认",
                        transaction.getId(),transaction.getAccount(),transaction.getAmount().toString(),rule);
                stringBuilder.append(notice).append("\n");
                kafkaProducerSender.sendMessage(notice);
            }
        }
        boolean result = true;
        for(boolean bb: fraud){
            result = result && !bb;
        }
        if(!result){
            FraudTransaction fraudTransaction = new FraudTransaction();
            fraudTransaction.setAlertDescription(stringBuilder.substring(0,stringBuilder.length()-2));
            fraudTransaction.setTransactionId(transaction.getId());
            fraudTransaction.setAlertType("1");
            fraudTransactionMapper.insert(fraudTransaction);
        }
        return result;
    }

    private void triggerAlert(Transaction transaction, String rule) {
        System.out.printf("Fraud detected for Transaction ID: %d transaction.getId()  by rule: %d", transaction.getId(), transaction.getTimestamp());
                // 触发通知机制（如短信、邮件等）
    }
}
