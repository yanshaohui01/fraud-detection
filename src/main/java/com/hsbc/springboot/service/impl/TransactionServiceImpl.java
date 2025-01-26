package com.hsbc.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsbc.springboot.core.RuleParser;
import com.hsbc.springboot.entity.FraudTransaction;
import com.hsbc.springboot.entity.Rule;
import com.hsbc.springboot.entity.Transaction;
import com.hsbc.springboot.mapper.FraudTransactionMapper;
import com.hsbc.springboot.mapper.TransactionsMapper;
import com.hsbc.springboot.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @className: TransactionServiceImpl
 * @package: com.hsbc.springboot.service.impl
 * @author: bruce
 * @date: 2025/1/26 13:22
 */
@Slf4j
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionsMapper, Transaction> implements TransactionService {

    @Autowired
    private TransactionsMapper transactionsMapper;

    @Autowired
    private FraudTransactionMapper fraudTransactionMapper;

    @Autowired
    private RuleParser ruleParser;

    @Override
    @Transactional
    public int saveTransaction(Transaction transaction) {
        boolean result = ruleParser.processTransaction(transaction);
        this.save(transaction);
        if(result){
            log.info("交易： {}  正常！",transaction.toString());
        }else{
            log.info(" 交易： {}  异常！",transaction.toString());
            FraudTransaction fraudTransaction = new FraudTransaction();
            fraudTransaction.setTransactionId(transaction.getId());
            fraudTransactionMapper.insert(fraudTransaction);
        }
        return 1;
    }

    @Override
    public int deleteTransaction(int id) {
        return transactionsMapper.deleteById(id);
    }

    @Override
    public int updateTransaction(Transaction transaction) {
        return transactionsMapper.updateById(transaction);
    }

    @Override
    public Transaction getTransactionById(int id) {
        return transactionsMapper.selectById(id);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionsMapper.selectList(null);
    }

    @Override
    public List<Transaction> getTransactionByCondition(Transaction transaction) {
        return null;
    }

    @Override
    public IPage<Transaction> getTransactionByPage(int pageNum, int pageSize) {
        Page<Transaction> page = new Page(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("statue",true);
        return transactionsMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Transaction> getTransactionByPageAndCondition(int pageNum, int pageSize, Transaction transaction) {
        return null;
    }
}
