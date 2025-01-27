package com.hsbc.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.entity.FraudTransaction;
import com.hsbc.springboot.entity.Transaction;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudTranscationService
 * @package: com.hsbc.springboot.service
 * @author: bruce
 * @date: 2025/1/26 13:55
 */
public interface FraudTranscationService {
    int updateById(FraudTransaction fraudTransaction);
    int save(FraudTransaction fraudTransaction);
    // 分页查询
    IPage<FraudTransaction> getFTByPage(int pageNum, int pageSize);
}
