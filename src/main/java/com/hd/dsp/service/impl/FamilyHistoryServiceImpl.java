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
}
