package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.DiseaseHistory;

import java.util.List;

public interface DiseaseHistoryService  extends IService<DiseaseHistory> {
    List<DiseaseHistory> getByUserId(Integer userId);

    int update(DiseaseHistory diseaseHistory);

    int insert(DiseaseHistory diseaseHistory);

    int deleteById(Integer id);
}
