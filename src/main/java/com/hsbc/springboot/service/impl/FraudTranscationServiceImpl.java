package com.hsbc.springboot.service.impl;

import com.hsbc.springboot.entity.FraudTransaction;
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
}
