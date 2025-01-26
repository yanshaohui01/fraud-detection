package com.hsbc.springboot.service;

import com.hsbc.springboot.entity.Transaction;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudDetectionService
 * @package: com.hsbc.springboot.service
 * @author: bruce
 * @date: 2025/1/24 14:48
 */
public interface FraudDetectionService {
    void analyzeTransaction(Transaction transaction);

    void handleComplexAnalysis(Transaction transaction);

}
