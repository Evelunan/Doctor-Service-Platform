package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.HealthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HealthInfoMapper extends BaseMapper<HealthInfo> {
    @Select("select * from health_info where user_id=#{userId}")
    public HealthInfo selectByIdUserId(Integer userId);
}
