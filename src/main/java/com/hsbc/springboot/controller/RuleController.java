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

/**
 * 〈功能概述〉<br>
 *
 * @className: RuleController
 * @package: com.hsbc.springboot.controller
 * @author: bruce
 * @date: 2025/1/24 17:52
 */
@Api(value = "规则配置 API", description = "这是一个简单的示例 API")
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
            @ApiImplicitParam(name = "account", value = "交易账户", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号"),
            @ApiImplicitParam(name = "qq", value = "qq号"),
            @ApiImplicitParam(name = "weixin", value = "微信号"),
            @ApiImplicitParam(name = "userCode", value = "用户编号")
    })
    public BaseResult saveRule(@RequestBody Rule rule){
        return BaseResult.success(ruleService.saveRule(rule));
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public BaseResult deleteRule(@PathVariable int id) {
        int result = ruleService.deleteRule(id);
        return BaseResult.success(result);
    }

    // 修改用户信息
    @PutMapping
    public BaseResult updateUser(@RequestBody Rule rule) {
        int result = ruleService.updateRule(rule);
        return BaseResult.success(result);
    }

    @GetMapping("/getRulePage")
    public BaseResult<IPage<Rule>> getRulePage(){
        IPage<Rule> ipage = ruleService.getRulesByPage(1,5);
        return BaseResult.success(ipage,"success");
    }

    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        try {
            emailSender.sendSimpleEmail(to, subject, text);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }



}
