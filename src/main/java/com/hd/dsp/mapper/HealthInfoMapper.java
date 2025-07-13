package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.HealthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HealthInfoMapper extends BaseMapper<HealthInfo> {
}
