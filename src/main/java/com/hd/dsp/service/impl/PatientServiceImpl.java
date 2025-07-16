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
import com.hd.dsp.utils.Md5Util;
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

    @Override
    public void addPatient(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        Long count = userMapper.selectCount(new QueryWrapper<User>().eq("account", user.getAccount()));
        if (count > 0){
            throw new RuntimeException("账户已存在");
        }
        user.setPassword(Md5Util.getMD5String(user.getAccount()));
        user.setType(1);
        user.setUpdated(true);
        userMapper.insert(user);
        userVo.getDiseaseHistoryList().forEach(diseaseHistory -> {
            diseaseHistory.setUserId(user.getId());
        });
        userVo.getFamilyHistoryList().forEach(familyHistory -> {
            familyHistory.setUserId(user.getId());
        });
        healthInfoMapper.insert(userVo.getHealthInfo());
        diseaseHistoryMapper.insert(userVo.getDiseaseHistoryList());
        familyHistoryMapper.insert(userVo.getFamilyHistoryList());
    }

    @Override
    public void updatePatient(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setUpdated(true);
        userMapper.updateById(user);
        healthInfoMapper.delete(new QueryWrapper<HealthInfo>().eq("user_id", user.getId()));
        diseaseHistoryMapper.delete(new QueryWrapper<DiseaseHistory>().eq("user_id", user.getId()));
        familyHistoryMapper.delete(new QueryWrapper<FamilyHistory>().eq("user_id", user.getId()));
        healthInfoMapper.insert(userVo.getHealthInfo());
        diseaseHistoryMapper.insert(userVo.getDiseaseHistoryList());
        familyHistoryMapper.insert(userVo.getFamilyHistoryList());
    }

    @Override
    public void deletePatient(Integer id) {

        userMapper.deleteById(id);
        healthInfoMapper.delete(new QueryWrapper<HealthInfo>().eq("user_id", id));
        diseaseHistoryMapper.delete(new QueryWrapper<DiseaseHistory>().eq("user_id", id));
        familyHistoryMapper.delete(new QueryWrapper<FamilyHistory>().eq("user_id", id));
    }
}
