package com.hd.dsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.WarningUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarningInfoMapper extends BaseMapper<WarningInfo> {
    Page<WarningUserVO> listWarningUsers(Page<WarningUserVO> page, Integer doctorId);
}
