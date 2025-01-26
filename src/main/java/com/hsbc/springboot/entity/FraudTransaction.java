package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: FraudTransaction
 * @package: com.hsbc.springboot.entity
 * @author: bruce
 * @date: 2025/1/23 23:24
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("fraud_transaction")
public class FraudTransaction  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer transactionId;
    private String alertType;
    private String alertDescription;
    private Date detectedAt;

}
