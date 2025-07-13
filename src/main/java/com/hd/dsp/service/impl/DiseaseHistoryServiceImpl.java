package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.DiseaseHistoryMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.service.DiseaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseHistoryServiceImpl extends ServiceImpl<DiseaseHistoryMapper, DiseaseHistory> implements DiseaseHistoryService {

    @Autowired
    private DiseaseHistoryMapper diseaseHistoryMapper;
    @Override
    public DiseaseHistory getByUserId(Integer userId) {
        return diseaseHistoryMapper.getByUserId(userId);
    }

    @Override
    public int update(DiseaseHistory diseaseHistory) {
        return diseaseHistoryMapper.updateById(diseaseHistory);
    }

    @Override
    public int insert(DiseaseHistory diseaseHistory) {
        return  diseaseHistoryMapper.insert(diseaseHistory);
    }

    @Override
    public int deleteById(Integer id) {
        return diseaseHistoryMapper.deleteById(id);
    }
}
