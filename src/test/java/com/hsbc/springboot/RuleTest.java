package com.hsbc.springboot;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.controller.RuleController;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.utils.BaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * 规则单元测试
 *
 * @className: RuleTest
 * @package: com.hsbc.springboot
 * @author: bruce
 * @date: 2025/1/26 13:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTest {

    @Autowired
    private RuleController ruleController;

    @Test
    public void save(){
        Rule rule = new Rule();
        rule.setCreatedAt(new Date());
        rule.setRuleName("测试规则3");
        rule.setRuleDescription("this is for testing3");
        rule.setUpdatedAt(new Date());
        rule.setState(true);
        ruleController.saveRule(rule);
    }

    @Test
    public void getAll(){
        BaseResult<IPage<Rule>> pageRule = ruleController.getRulePage();
        List<Rule> lists = pageRule.getData().getRecords();
        lists.forEach(System.out::println);
    }

    @Test
    public void del(){
        BaseResult baseResult = ruleController.deleteRule(4);
        System.out.println(baseResult.getCode());
    }

    @Test
    public void update(){
        Rule rule = new Rule();
        rule.setId(5);
        rule.setRuleName("测试规则1");
        BaseResult baseResult = ruleController.updateRule(rule);
        System.out.println(baseResult.getCode());
    }
}
