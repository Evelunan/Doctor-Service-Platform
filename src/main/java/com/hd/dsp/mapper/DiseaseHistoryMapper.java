package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DiseaseHistoryMapper extends BaseMapper<DiseaseHistory> {
}
