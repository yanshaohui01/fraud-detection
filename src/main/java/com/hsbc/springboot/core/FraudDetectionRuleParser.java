package com.hsbc.springboot.core;

import com.hsbc.springboot.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudDetectionRuleParser
 * @package: com.hsbc.springboot.core
 * @author: bruce
 * @date: 2025/1/24 19:51
 */
@Component
public class FraudDetectionRuleParser {
    private ExpressionParser parser = new SpelExpressionParser();

    public boolean evaluateRule(Transaction transaction, String ruleExpression) {
        StandardEvaluationContext context = new StandardEvaluationContext(transaction);
        Expression expression = parser.parseExpression(ruleExpression);
        return expression.getValue(context, Boolean.class);
    }
}
