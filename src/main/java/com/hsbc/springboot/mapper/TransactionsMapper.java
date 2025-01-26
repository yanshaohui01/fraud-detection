package com.hsbc.springboot.mapper;

/**
 * 〈功能概述〉<br>
 *
 * @className: TransactionsMapper
 * @package: com.hsbc.springboot.mapper
 * @author: bruce
 * @date: 2025/1/23 23:27
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.springboot.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionsMapper extends BaseMapper<Transaction> {
}
