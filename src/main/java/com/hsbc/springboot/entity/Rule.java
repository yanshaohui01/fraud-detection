package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: Rule
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
@TableName("rule")
public class Rule  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String ruleName;
    private String ruleDescription;
    private Date createdAt;
    private Date updatedAt;
    private boolean state;

}
