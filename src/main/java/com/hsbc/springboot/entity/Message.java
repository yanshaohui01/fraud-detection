package com.hsbc.springboot.entity;

/**
 * 〈功能概述〉<br>
 *
 * @className: Message
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
@TableName("message")
public class Message  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer alertId;
    private String recipient;
    private String messageContent;
    private Date sentTime;
    private String status;

}
