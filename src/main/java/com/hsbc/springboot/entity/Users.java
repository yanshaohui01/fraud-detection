package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: Users
 * @package: com.hsbc.springboot.entity
 * @author: bruce
 * @date: 2025/1/23 23:23
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("users")
public class Users  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

}
