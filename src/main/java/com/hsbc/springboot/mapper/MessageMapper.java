package com.hsbc.springboot.mapper;

/**
 * 〈功能概述〉<br>
 *
 * @className: MessageMapper
 * @package: com.hsbc.springboot.mapper
 * @author: bruce
 * @date: 2025/1/23 23:29
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.springboot.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
