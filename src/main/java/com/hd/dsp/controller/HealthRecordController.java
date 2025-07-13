package com.hd.dsp.controller;

import com.hd.dsp.mapper.DiseaseHistoryMapper;
import com.hd.dsp.mapper.HealthInfoMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.pojo.HealthInfo;
import com.hd.dsp.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elder/healthRecord")
public class HealthRecordController {
    @Autowired
    private HealthInfoMapper healthInfoMapper;
    @Autowired
    private DiseaseHistoryMapper diseaseHistoryMapper;
    //获得基本健康信息
    @GetMapping("/baseInfo/{id}")
    public Result getBaseInfo(@PathVariable("id") Integer userId){
        return Result.success(healthInfoMapper.selectByIdUserId(userId));
    }

    //更新基本健康信息

    @PutMapping("/baseInfo/update")
    public Result updateBaseInfo(@RequestBody HealthInfo healthInfo){
        return healthInfoMapper.updateById(healthInfo) > 0 ? Result.success() : Result.error("更新失败！");
    }

    //获得疾病历史信息
    @GetMapping("diseaseHistory/{id}")
    public Result getDiseaseHistory(@PathVariable("id") Integer userId){
        return Result.success(diseaseHistoryMapper.selectByIdUserId(userId));
    }
    //更新疾病历史信息
    @PutMapping("/diseaseHistory/update")
    public Result updateDiseaseHistory(@RequestBody DiseaseHistory diseaseHistory){
        return diseaseHistoryMapper.updateById(diseaseHistory) > 0 ? Result.success() : Result.error("更新失败！");
    }

    //添加疾病历史信息
    @PostMapping("/diseaseHistory/add")
    public Result addDiseaseHistory(@RequestBody DiseaseHistory diseaseHistory){
        return diseaseHistoryMapper.insert(diseaseHistory) > 0 ? Result.success() : Result.error("添加失败！");
    }
}
