package com.hsbc.springboot.mapper;

/**
 * 〈功能概述〉<br>
 *
 * @className: ReportDailyMapper
 * @package: com.hsbc.springboot.mapper
 * @author: bruce
 * @date: 2025/1/23 23:30
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.springboot.entity.ReportDaily;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportDailyMapper extends BaseMapper<ReportDaily> {
}
