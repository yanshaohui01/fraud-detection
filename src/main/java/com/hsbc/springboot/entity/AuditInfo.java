package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: AuditInfo
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
@TableName("audit_info")
public class AuditInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer transactionId;
    private String auditor;
    private String auditStatus;
    private String auditComments;
    private Date auditTime;

}
