package com.hsbc.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.core.EmailSender;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.service.RuleService;
import com.hsbc.springboot.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 〈功能概述〉<br>
 *
 * @className: RuleController
 * @package: com.hsbc.springboot.controller
 * @author: bruce
 * @date: 2025/1/24 17:52
 */
@Api(value = "规则配置业务  RESTful API 接口", description = "规则配置业务相关接口，仅供参考")
@RequestMapping("/rule")
@RestController
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/save")
    @ApiOperation(value = "保存规则", notes = "保存规则用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruleName", value = "规则名称", required = true),
            @ApiImplicitParam(name = "ruleDescription", value = "规则说明", required = true),
            @ApiImplicitParam(name = "createdAt", value = "创建时间"),
            @ApiImplicitParam(name = "updatedAt", value = "更新时间"),
            @ApiImplicitParam(name = "state", value = "状态 0 废弃，1生效 默认1")
    })
    public BaseResult saveRule(@ApiIgnore @RequestBody Rule rule){
        return BaseResult.success(ruleService.saveRule(rule));
    }

    // 删除用户
    @GetMapping("/del/{id}")
    @ApiOperation(value = "根据ID删除规则", notes = "删除规则用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则id", required = true)
    })
    public BaseResult deleteRule(@PathVariable int id) {
        int result = ruleService.deleteRule(id);
        return BaseResult.success(result);
    }

    // 修改用户信息
    @PostMapping("/update")
    @ApiOperation(value = "根据ID更新规则", notes = "更新规则用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则id", required = true),
            @ApiImplicitParam(name = "ruleName", value = "规则名称", required = true),
            @ApiImplicitParam(name = "ruleDescription", value = "规则说明", required = true),
            @ApiImplicitParam(name = "createdAt", value = "创建时间"),
            @ApiImplicitParam(name = "updatedAt", value = "更新时间"),
            @ApiImplicitParam(name = "state", value = "状态 0 废弃，1生效", required = true)
    })
    public BaseResult updateRule(@ApiIgnore @RequestBody Rule rule) {
        int result = ruleService.updateRule(rule);
        return BaseResult.success(result);
    }

    @ApiOperation(value = "分页无条件查询规则", notes = "查询规则用于测试")
    @GetMapping("/getRulePage")
    public BaseResult<IPage<Rule>> getRulePage(){
        IPage<Rule> ipage = ruleService.getRulesByPage(1,5);
        return BaseResult.success(ipage,"success");
    }

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String subject, @RequestParam String text) {
        try {
            emailSender.sendSimpleEmail(subject, text);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }



}
