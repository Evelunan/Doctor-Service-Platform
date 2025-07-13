package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.HealthInfoMapper;
import com.hd.dsp.pojo.HealthInfo;
import com.hd.dsp.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthInfoServiceImpl extends ServiceImpl<HealthInfoMapper, HealthInfo> implements HealthInfoService {
    @Autowired
    private HealthInfoMapper healthInfoMapper;
    @Override
    public HealthInfo getByUserId(Integer userId) {
        return healthInfoMapper.getByUserId(userId);
    }

    @Override
    public int update(HealthInfo healthInfo) {
        return healthInfoMapper.updateByUserId(healthInfo);
    }

    @Override
    public int insert(HealthInfo healthInfo) {
        return  healthInfoMapper.insert(healthInfo);
    }
}
