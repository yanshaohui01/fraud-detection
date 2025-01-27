package com.hsbc.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.entity.FraudTransaction;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.service.FraudTranscationService;
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
 * @className: FraudTransactionController
 * @package: com.hsbc.springboot.controller
 * @author: bruce
 * @date: 2025/1/27 10:29
 */
@Api(value = "异常交易业务 RESTful API 接口", description = "异常交易业务相关接口，仅供参考")
@RequestMapping("/fraudTransaction")
@RestController
public class FraudTransactionController {

    @Autowired
    private FraudTranscationService fraudTranscationService;

    // 修改用户信息
    @PostMapping("/update")
    @ApiOperation(value = "根据id更新异常交易记录", notes = "更新异常交易状态用于测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "规则id", required = true),
            @ApiImplicitParam(name = "transactionId", value = "规则名称"),
            @ApiImplicitParam(name = "alertType", value = "类型 1-转账交易"),
            @ApiImplicitParam(name = "alertDescription", value = "描述"),
            @ApiImplicitParam(name = "updatedAt", value = "更新时间"),
            @ApiImplicitParam(name = "state", value = "状态 1 待审核，2 已审核 ")
    })
    public BaseResult updateFT(@ApiIgnore @RequestBody FraudTransaction fraudTransaction) {
        int result = fraudTranscationService.updateById(fraudTransaction);
        return BaseResult.success(result);
    }

    @ApiOperation(value = "分页无条件查询异常交易记录", notes = "查询异常交易记录用于测试")
    @GetMapping("/getFTPage")
    public BaseResult<IPage<FraudTransaction>> getFTPage(){
        IPage<FraudTransaction> ipage = fraudTranscationService.getFTByPage(1,5);
        return BaseResult.success(ipage,"success");
    }
}
