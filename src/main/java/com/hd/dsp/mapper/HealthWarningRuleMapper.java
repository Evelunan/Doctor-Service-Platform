package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.HealthWarningRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthWarningRuleMapper extends BaseMapper<HealthWarningRule> {
    List<HealthWarningRule> selectList(QueryWrapper<Object> userId);
}
