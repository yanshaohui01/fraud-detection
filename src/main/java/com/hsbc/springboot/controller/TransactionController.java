package com.hsbc.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.core.EmailSender;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.service.RuleService;
import com.hsbc.springboot.service.TransactionService;
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
 * @className: TransactionController
 * @package: com.hsbc.springboot.controller
 * @author: bruce
 * @date: 2025/1/26 15:16
 */
@Api(value = "交易业务 RESTful API 接口", description = "交易业务相关接口，仅供参考")
@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    @ApiOperation(value = "保存规则", notes = "保存规则用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruleName", value = "规则名称", required = true),
            @ApiImplicitParam(name = "ruleDescription", value = "规则说明", required = true),
            @ApiImplicitParam(name = "createdAt", value = "创建时间"),
            @ApiImplicitParam(name = "updatedAt", value = "更新时间"),
            @ApiImplicitParam(name = "state", value = "状态 0 废弃，1生效", required = true)
    })
    public BaseResult saveTransaction(@ApiIgnore @RequestBody Transaction rule){
        return BaseResult.success(transactionService.saveTransaction(rule));
    }

    // 删除用户
    @GetMapping("/del/{id}")
    @ApiOperation(value = "根据ID删除规则", notes = "删除规则用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则id", required = true)
    })
    public BaseResult deleteTransaction(@PathVariable int id) {
        int result = transactionService.deleteTransaction(id);
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
    public BaseResult updateTransaction(@ApiIgnore @RequestBody Transaction rule) {
        int result = transactionService.updateTransaction(rule);
        return BaseResult.success(result);
    }

    @ApiOperation(value = "分页无条件查询规则", notes = "查询规则用于测试")
    @GetMapping("/getRulePage")
    public BaseResult<IPage<Transaction>> getRulePage(){
        IPage<Transaction> ipage = transactionService.getTransactionByPage(1,5);
        return BaseResult.success(ipage,"success");
    }
}
