package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.FamilyHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FamilyHistoryMapper extends BaseMapper<FamilyHistory> {
    @Select("select * from family_history where user_id = #{userId}")
    List<FamilyHistory> getByUserId(Integer userId);

    @Update("update family_history set relation=#{relation},disease_name=#{diseaseName},notes=#{notes} where user_id=#{userId} and id=#{id}")
    int updateByTwoId(FamilyHistory familyHistory);
}
