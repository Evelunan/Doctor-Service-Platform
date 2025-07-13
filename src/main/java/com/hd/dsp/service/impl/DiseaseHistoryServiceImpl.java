package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.DiseaseHistoryMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.service.DiseaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseHistoryServiceImpl extends ServiceImpl<DiseaseHistoryMapper, DiseaseHistory> implements DiseaseHistoryService {

}
