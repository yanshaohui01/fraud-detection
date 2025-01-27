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
    @ApiOperation(value = "交易接口", notes = "进行交易接口，用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额", required = true),
            @ApiImplicitParam(name = "account", value = "账户", required = true),
            @ApiImplicitParam(name = "country", value = "交易地区：国家"),
            @ApiImplicitParam(name = "transactionType", value = "默认值1，转账交易"),
            @ApiImplicitParam(name = "timestamp", value = "交易时间"),
            @ApiImplicitParam(name = "state", value = "状态 0 废弃，1生效,默认1")
    })
    public BaseResult saveTransaction(@ApiIgnore @RequestBody Transaction rule){
        return BaseResult.success(transactionService.saveTransaction(rule));
    }

    // 删除用户
    @GetMapping("/del/{id}")
    @ApiOperation(value = "根据ID删除交易记录", notes = "删除交易用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则id", required = true)
    })
    public BaseResult deleteTransaction(@PathVariable int id) {
        int result = transactionService.deleteTransaction(id);
        return BaseResult.success(result);
    }

    // 修改用户信息
    @PostMapping("/update")
    @ApiOperation(value = "根据ID更新交易记录", notes = "更新交易记录用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额", required = true),
            @ApiImplicitParam(name = "account", value = "账户", required = true),
            @ApiImplicitParam(name = "country", value = "交易地区：国家"),
            @ApiImplicitParam(name = "transactionType", value = "默认值1，转账交易"),
            @ApiImplicitParam(name = "timestamp", value = "交易时间"),
            @ApiImplicitParam(name = "state", value = "状态 0 废弃，1生效,默认1")
    })
    public BaseResult updateTransaction(@ApiIgnore @RequestBody Transaction rule) {
        int result = transactionService.updateTransaction(rule);
        return BaseResult.success(result);
    }

    @ApiOperation(value = "分页无条件查询交易记录", notes = "查询交易记录用于测试")
    @GetMapping("/getRulePage")
    public BaseResult<IPage<Transaction>> getRulePage(){
        IPage<Transaction> ipage = transactionService.getTransactionByPage(1,5);
        return BaseResult.success(ipage,"success");
    }
}
