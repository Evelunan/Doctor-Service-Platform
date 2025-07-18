package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.dsp.pojo.User;
import com.hd.dsp.pojo.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set password = #{newPassword} where id = #{id}")
    public boolean updatePassword(Integer id, String newPassword);

    @Select({
            "SELECT",
            "f.plan_time,",
            "f.priority,",
            "f.method,",
            "u.username AS doctorName",
            "FROM followup_plan f",
            "LEFT JOIN user u ON f.doctor_id = u.id",
            "WHERE f.elder_id = #{elderId}"
    })
    List<NoticeVO> getNotice(Integer elderId);
}
