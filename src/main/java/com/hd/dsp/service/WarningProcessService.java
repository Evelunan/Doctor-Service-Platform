package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.WarningUserVO;

import java.util.List;

public interface WarningProcessService extends IService<WarningInfo> {
    Page<WarningUserVO> listWarningUsers(Page<WarningUserVO> page, Integer doctorId);

    void inspect();
}
