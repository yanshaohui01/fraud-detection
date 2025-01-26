package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: ReportDaily
 * @package: com.hsbc.springboot.entity
 * @author: bruce
 * @date: 2025/1/23 23:26
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("report_daily")
public class ReportDaily  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Date reportDate;
    private Integer totalTransactions;
    private Integer totalAlerts;
    private Integer resolvedAlerts;
    private Integer unresolvedAlerts;
    private Date createdAt;
}
