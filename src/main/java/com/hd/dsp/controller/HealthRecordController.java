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

    @PutMapping("/baseInfo/update")
    public Result updateBaseInfo(@RequestBody HealthInfo healthInfo){
        return healthInfoService.update(healthInfo) > 0 ? Result.success() : Result.error("更新失败！");
    }

    @PostMapping("/baseInfo/add")
    public Result addBaseInfo(@RequestBody HealthInfo healthInfo){
        return healthInfoService.insert(healthInfo) > 0 ? Result.success() : Result.error("添加基本信息失败！");
    }

    //获得疾病历史信息
    @GetMapping("/diseaseHistory/{id}")
    public Result getDiseaseHistory(@PathVariable("id") Integer userId){
        return Result.success(diseaseHistoryService.getByUserId(userId));
    }
    //更新疾病历史信息
    @PutMapping("/diseaseHistory/update")
    public Result updateDiseaseHistory(@RequestBody DiseaseHistory diseaseHistory){
        return diseaseHistoryService.update(diseaseHistory) > 0 ? Result.success() : Result.error("更新失败！");
    }

    //添加疾病历史信息
    @PostMapping("/diseaseHistory/add")
    public Result addDiseaseHistory(@RequestBody DiseaseHistory diseaseHistory){
        return diseaseHistoryService.insert(diseaseHistory) > 0 ? Result.success() : Result.error("添加失败！");
    }

    @DeleteMapping("/diseaseHistory/delete/{id}")
    public Result deleteDiseaseHistory(@PathVariable("id") Integer id){
        return diseaseHistoryService.deleteById(id) > 0? Result.success() : Result.error("删除失败！");
    }
}
