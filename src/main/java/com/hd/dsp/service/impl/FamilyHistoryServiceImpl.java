package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.FamilyHistoryMapper;
import com.hd.dsp.pojo.FamilyHistory;
import com.hd.dsp.service.FamilyHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyHistoryServiceImpl extends ServiceImpl<FamilyHistoryMapper, FamilyHistory> implements FamilyHistoryService {
    @Autowired
    private FamilyHistoryMapper familyHistoryMapper;
    @Override
    public List<FamilyHistory> getByUserId(Integer userId) {
        return familyHistoryMapper.getByUserId(userId);
    }

    @Override
    public int update(FamilyHistory familyHistory) {
        return familyHistoryMapper.updateByTwoId(familyHistory);
    }

    @Override
    public int insert(FamilyHistory familyHistory) {
        return familyHistoryMapper.insert(familyHistory);
    }
}
