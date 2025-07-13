package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.DiseaseHistory;

public interface DiseaseHistoryService  extends IService<DiseaseHistory> {
    DiseaseHistory getByUserId(Integer userId);

    int update(DiseaseHistory diseaseHistory);

    int insert(DiseaseHistory diseaseHistory);

    int deleteById(Integer id);
}
