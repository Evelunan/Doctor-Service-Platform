package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.FamilyHistory;

import java.util.List;

public interface FamilyHistoryService extends IService<FamilyHistory> {
    List<FamilyHistory> getByUserId(Integer userId);

    int update(FamilyHistory familyHistory);

    int insert(FamilyHistory familyHistory);
}
