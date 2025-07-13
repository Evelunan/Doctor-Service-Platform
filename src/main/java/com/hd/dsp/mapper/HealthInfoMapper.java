package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.HealthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HealthInfoMapper extends BaseMapper<HealthInfo> {
    @Select("select * from health_info where user_id=#{userId}")
    HealthInfo getByUserId(Integer userId);

    @Update("update health_info set height=#{height},weight=#{weight},blood_type=#{bloodType},allergies=#{allergies},disability=#{disability} where user_id=#{userId}")
    int updateByUserId(HealthInfo healthInfo);
}
