package com.hsbc.springboot.mapper;

/**
 * 〈功能概述〉<br>
 *
 * @className: AuditorMapper
 * @package: com.hsbc.springboot.mapper
 * @author: bruce
 * @date: 2025/1/23 23:28
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.springboot.entity.Auditor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuditorMapper extends BaseMapper<Auditor> {
}
