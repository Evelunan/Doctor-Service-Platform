package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DiseaseHistoryMapper extends BaseMapper<DiseaseHistory> {
    @Select("select * from disease_history where user_id=#{userId}")
    List<DiseaseHistory> getByUserId(Integer userId);

    @Update("update disease_history set disease_name=#{diseaseName},diagnosis_date=#{diagnosisDate},status=#{status},notes=#{notes} where user_id=#{userId} and id=#{id}")
    int updateByUserId(DiseaseHistory diseaseHistory);
}
