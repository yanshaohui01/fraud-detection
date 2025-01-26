package com.hsbc.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.springboot.config.GlobalVariable;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.mapper.RuleMapper;
import com.hsbc.springboot.service.RuleService;
import io.netty.util.internal.StringUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: RulesServiceImpl
 * @package: com.hsbc.springboot.service.impl
 * @author: bruce
 * @date: 2025/1/24 17:54
 */
@Service
public class RulesServiceImpl implements RuleService {

    @Autowired
    RuleMapper ruleMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Rule getRuleById(int id) {
        Rule result = ruleMapper.selectById(id);
        return result;
    }

    @Override
    public int saveRule(Rule rule) {
        HashOperations<String,Object,Object> hasOps = redisTemplate.opsForHash();
        int result = ruleMapper.insert(rule);
        if(result > 0){
            hasOps.put(GlobalVariable.rulesHash,rule.getRuleName(),rule.getRuleDescription());
        }
        return result;
    }

    @Override
    public int deleteRule(int id) {
        HashOperations<String,Object,Object> hasOps = redisTemplate.opsForHash();
        Rule rule = ruleMapper.selectById(id);
        int result = ruleMapper.deleteById(id);
        if(result > 0){
            hasOps.delete(GlobalVariable.rulesHash,rule.getRuleName());
        }
        return result;
    }

    @Override
    public int updateRule(Rule rule) {
        HashOperations<String,Object,Object> hasOps = redisTemplate.opsForHash();
        int result = ruleMapper.updateById(rule);
        if(result > 0){
            Rule newRule = getRuleById(rule.getId());
            hasOps.delete(GlobalVariable.rulesHash,newRule.getRuleName(),newRule.getRuleDescription());
        }
        return result;
    }

    @Override
    public List<Rule> getAllRule() {
        return ruleMapper.selectList(null);
    }

    @Override
    public List<Rule> getRuleByCondition(Rule rule) {
        return null;
    }

    @Override
    public IPage<Rule> getRulesByPage(int pageNum, int pageSize) {
        Page<Rule> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNotNull("rule_name");
        queryWrapper.eq("state",true);
        return ruleMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Rule> getRulesByPageAndCondition(int pageNum, int pageSize, Rule rule) {
        Page<Rule> page = new Page<>(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state",true);
        if(StringUtil.isNullOrEmpty(rule.getRuleName())){
            queryWrapper.eq("rule_name",rule.getRuleName());
        }
        if(!ObjectUtils.isEmpty(rule.getId())){
            queryWrapper.eq("id",rule.getId());
        }
        return ruleMapper.selectPage(page,queryWrapper);
    }
}
