package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.WarningUserVO;

import java.util.List;

public interface WarningProcessService extends IService<WarningInfo> {
    List<WarningUserVO> listWarningUsers(Integer doctorId);
}
