package com.hd.dsp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hd.dsp.pojo.HealthWarningRule;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.constant.HealthWarningType;
import com.hd.dsp.service.HealthWarningRuleService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/healthWarning")
public class HealthWarningController {
    @Autowired
    private HealthWarningRuleService healthWarningRuleService;
    @GetMapping("/baseList")
    public Result baseList() {
        List<HealthWarningRule> healthWarningRules = healthWarningRuleService.listBaseRules();
        return Result.success(healthWarningRules);
    }
    @PostMapping("/save")
    public Result baseSave(@RequestBody List<HealthWarningRule> rules){
        return healthWarningRuleService.saveOrUpdateBatch(rules) ? Result.success() : Result.error("保存失败");
    }

    @GetMapping("/diseaseList")
    public Result diseaseList() {
        QueryWrapper<HealthWarningRule> queryWrapper = new QueryWrapper<>();
//                .eq("type", HealthWarningType.DISEASE_HISTORY);
        queryWrapper.eq("type", HealthWarningType.DISEASE_HISTORY);
        queryWrapper.eq("user_id", UserContext.getUserId());
        List<HealthWarningRule> healthWarningRules = healthWarningRuleService.list(queryWrapper);
        return Result.success(healthWarningRules);
    }
    @GetMapping("/familyList")
    public Result familyList() {
        QueryWrapper<HealthWarningRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", HealthWarningType.FAMILY_HISTORY);
        queryWrapper.eq("user_id", UserContext.getUserId());
        List<HealthWarningRule> healthWarningRules = healthWarningRuleService.list(queryWrapper);
        return Result.success(healthWarningRules);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        return healthWarningRuleService.removeById(id) ? Result.success() : Result.error("删除失败");
    }
}
