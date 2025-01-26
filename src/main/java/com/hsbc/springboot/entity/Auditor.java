package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: Auditor
 * @package: com.hsbc.springboot.entity
 * @author: bruce
 * @date: 2025/1/23 23:24
 */
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("auditor")
public class Auditor  implements Serializable {
    private String username;
    private Integer level;
    private String email;
    private String phone;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

}