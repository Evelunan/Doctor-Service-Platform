package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiseaseHistoryMapper extends BaseMapper<DiseaseHistory> {
    @Select("select * from disease_history where user_id=#{userId}")
    DiseaseHistory getByUserId(Integer userId);
}
