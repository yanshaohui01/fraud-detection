package com.hsbc.springboot.core;

import com.hsbc.springboot.entity.Transaction;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 解析规则，分配至不同的过滤器
 *
 * @className: RuleParser
 * @package: com.hsbc.springboot.core
 * @author: bruce
 * @date: 2025/1/24 16:36
 */
@Component
public class RuleParser {
    // 模拟从数据库加载规则
    private List<String> loadRulesFromDB() {
        return Arrays.asList(
                "amount > 10000",
                "country.equals('US') == false",
                ""
        );
    }

    public boolean evaluateRule(Transaction transaction, String ruleExpression) {
        // 初始化表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 配置上下文并设置变量
        StandardEvaluationContext context = new StandardEvaluationContext(transaction);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

//        context.setVariable("transaction", transaction);
        // 解析表达式并求值
        Expression expression = parser.parseExpression(ruleExpression);
        return expression.getValue(context, Boolean.class);
    }

    public void processTransaction(Transaction transaction) {
        List<String> rules = loadRulesFromDB();

        for (String rule : rules) {
            boolean isFraud = evaluateRule(transaction, rule);

            if (isFraud) {
                triggerAlert(transaction, rule);
                return;
            }
        }
    }

    private void triggerAlert(Transaction transaction, String rule) {
        System.out.printf("Fraud detected for Transaction ID: %d transaction.getId()  by rule: %d", transaction.getId(), transaction.getUserId());
                // 触发通知机制（如短信、邮件等）
    }
}
