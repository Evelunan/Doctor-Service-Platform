package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hd.dsp.mapper.DiseaseHistoryMapper;
import com.hd.dsp.mapper.FamilyHistoryMapper;
import com.hd.dsp.mapper.HealthInfoMapper;
import com.hd.dsp.mapper.UserMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.pojo.FamilyHistory;
import com.hd.dsp.pojo.HealthInfo;
import com.hd.dsp.pojo.User;
import com.hd.dsp.pojo.vo.UserVo;
import com.hd.dsp.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    HealthInfoMapper healthInfoMapper;

    @Autowired
    DiseaseHistoryMapper diseaseHistoryMapper;

    @Autowired
    FamilyHistoryMapper familyHistoryMapper;
    @Override
    public UserVo getPatientArchive(Integer id) {
        UserVo userVo = new UserVo();
        User user = userMapper.selectById( id);
        BeanUtils.copyProperties(user, userVo);
        HealthInfo healthInfo = healthInfoMapper.selectOne(new QueryWrapper<HealthInfo>().eq("user_id", id));
        List<DiseaseHistory> diseaseHistoryList = diseaseHistoryMapper.selectList(new QueryWrapper<DiseaseHistory>().eq("user_id", id));
        List<FamilyHistory> familyHistoryList = familyHistoryMapper.selectList(new QueryWrapper<FamilyHistory>().eq("user_id", id));

        userVo.setHealthInfo(healthInfo);
        userVo.setDiseaseHistoryList(diseaseHistoryList);
        userVo.setFamilyHistoryList(familyHistoryList);

        return userVo;
    }
}
