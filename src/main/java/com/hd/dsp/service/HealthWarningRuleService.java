package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.HealthWarningRule;

import java.util.List;

public interface HealthWarningRuleService extends IService<HealthWarningRule> {

    public List<HealthWarningRule> listBaseRules();
}
