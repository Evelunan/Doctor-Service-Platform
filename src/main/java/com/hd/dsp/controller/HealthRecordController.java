package com.hd.dsp.controller;

import com.hd.dsp.mapper.DiseaseHistoryMapper;
import com.hd.dsp.mapper.HealthInfoMapper;
import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.pojo.HealthInfo;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.service.DiseaseHistoryService;
import com.hd.dsp.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elder/healthRecord")
public class HealthRecordController {
    @Autowired
    private HealthInfoService healthInfoService;
    @Autowired
    private DiseaseHistoryService diseaseHistoryService;
    //获得基本健康信息
    @GetMapping("/baseInfo/{id}")
    public Result getBaseInfo(@PathVariable("id") Integer userId){
        return Result.success(healthInfoService.getByUserId(userId));
    }

    //更新基本健康信息

    @PutMapping("/baseInfo/update/{id}")
    public Result updateBaseInfo(@PathVariable("id") Integer userId,@RequestBody HealthInfo healthInfo){
        healthInfo.setUserId(userId);
        return healthInfoService.update(healthInfo) > 0 ? Result.success() : Result.error("更新失败！");
    }

    @PostMapping("/baseInfo/add/{id}")
    public Result addBaseInfo(@PathVariable("id") Integer userId,@RequestBody HealthInfo healthInfo){
        healthInfo.setUserId(userId);
        return healthInfoService.insert(healthInfo) > 0 ? Result.success() : Result.error("添加基本信息失败！");
    }

    //获得疾病历史信息
    @GetMapping("/diseaseHistory/{id}")
    public Result getDiseaseHistory(@PathVariable("id") Integer userId){
        return Result.success(diseaseHistoryService.getByUserId(userId));
    }
    //更新疾病历史信息
    @PutMapping("/diseaseHistory/update/{id}/{diseaseId}")
    public Result updateDiseaseHistory(@PathVariable("id") Integer userId,@PathVariable("diseaseId") Integer diseaseId,@RequestBody DiseaseHistory diseaseHistory){
        diseaseHistory.setUserId(userId);
        diseaseHistory.setId(diseaseId);
        return diseaseHistoryService.update(diseaseHistory) > 0 ? Result.success() : Result.error("更新失败！");
    }

    //添加疾病历史信息
    @PostMapping("/diseaseHistory/add/{id}")
    public Result addDiseaseHistory(@PathVariable("id") Integer userId,@RequestBody DiseaseHistory diseaseHistory){
        diseaseHistory.setUserId(userId);
        return diseaseHistoryService.insert(diseaseHistory) > 0 ? Result.success() : Result.error("添加失败！");
    }

    @DeleteMapping("/diseaseHistory/delete/{id}/{diseaseId}")
    public Result deleteDiseaseHistory(@PathVariable("diseaseId") Integer diseaseId){
        return diseaseHistoryService.deleteById(diseaseId) > 0? Result.success() : Result.error("删除失败！");
    }
}
