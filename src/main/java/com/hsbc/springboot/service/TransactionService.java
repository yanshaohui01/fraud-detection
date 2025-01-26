package com.hsbc.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.entity.Transaction;

import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: TransactionService
 * @package: com.hsbc.springboot.service
 * @author: bruce
 * @date: 2025/1/26 13:20
 */
public interface TransactionService {

    int saveTransaction(Transaction transaction);

    int deleteTransaction(int id);

    int updateTransaction(Transaction transaction);

    Transaction getTransactionById(int id);

    List<Transaction> getAllTransaction();

    List<Transaction> getTransactionByCondition(Transaction transaction);
    // 分页查询
    IPage<Transaction> getTransactionByPage(int pageNum, int pageSize);
    // 分页条件查询
    IPage<Transaction> getTransactionByPageAndCondition(int pageNum, int pageSize, Transaction transaction);
}
