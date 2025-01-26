package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: Transactions
 * @package: com.hsbc.springboot.entity
 * @author: bruce
 * @date: 2025/1/23 23:23
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("transactions")
public class Transaction implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private BigDecimal amount;
    private String account;
    private String transactionType;
    private Date timestamp;
    private String status;
    private String country;

}
