package com.hsbc.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.entity.Rule;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: RoleService
 * @package: com.hsbc.springboot.service
 * @author: bruce
 * @date: 2025/1/24 17:53
 */
public interface RuleService {

    int saveRule(Rule rule);

    int deleteRule(int id);

    int updateRule(Rule rule);

    Rule getRuleById(int id);

    List<Rule> getAllRule();

    List<Rule> getRuleByCondition(Rule rule);
    // 分页查询
    IPage<Rule> getRulesByPage(int pageNum,int pageSize);
    // 分页条件查询
    IPage<Rule> getRulesByPageAndCondition(int pageNum, int pageSize, Rule rule);




}
