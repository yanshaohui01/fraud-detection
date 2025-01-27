package com.hsbc.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.springboot.entity.FraudTransaction;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.mapper.FraudTransactionMapper;
import com.hsbc.springboot.service.FraudDetectionService;
import com.hsbc.springboot.service.FraudTranscationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudTranscationServiceImpl
 * @package: com.hsbc.springboot.service.impl
 * @author: bruce
 * @date: 2025/1/26 13:57
 */
@Service
public class FraudTranscationServiceImpl implements FraudTranscationService {


    @Autowired
    private FraudTransactionMapper fraudTransactionMapper;

    @Override
    public int updateById(FraudTransaction fraudTransaction) {
        return  this.fraudTransactionMapper.updateById(fraudTransaction);
    }

    @Override
    public int save(FraudTransaction fraudTransaction) {
        return this.fraudTransactionMapper.insert(fraudTransaction);
    }

    @Override
    public IPage<FraudTransaction> getFTByPage(int pageNum, int pageSize) {
        Page<FraudTransaction> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("statue","1");
        return fraudTransactionMapper.selectPage(page,queryWrapper);
    }
}
