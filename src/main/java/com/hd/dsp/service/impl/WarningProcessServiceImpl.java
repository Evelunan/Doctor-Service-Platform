package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.UserMapper;
import com.hd.dsp.mapper.WarningInfoMapper;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.WarningUserVO;
import com.hd.dsp.service.WarningProcessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarningProcessServiceImpl extends ServiceImpl<WarningInfoMapper, WarningInfo> implements WarningProcessService {
    private final UserMapper userMapper;
    private final WarningInfoMapper warningInfoMapper;

    public WarningProcessServiceImpl(UserMapper userMapper, WarningInfoMapper warningInfoMapper) {
        this.userMapper = userMapper;
        this.warningInfoMapper = warningInfoMapper;
    }

    @Override
    public Page<WarningUserVO> listWarningUsers(Page<WarningUserVO> page, Integer doctorId) {

        return warningInfoMapper.listWarningUsers(page, doctorId);
    }
}
