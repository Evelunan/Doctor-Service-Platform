package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.HealthWarningRuleMapper;
import com.hd.dsp.pojo.HealthWarningRule;
import com.hd.dsp.pojo.constant.HealthWarningRuleName;
import com.hd.dsp.pojo.constant.HealthWarningType;
import com.hd.dsp.service.HealthWarningRuleService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HealthWarningRuleServiceImpl extends ServiceImpl<HealthWarningRuleMapper, HealthWarningRule> implements HealthWarningRuleService {
    private static final List<HealthWarningRule> DEFAULT_RULES = Arrays.asList(
            new HealthWarningRule(null, HealthWarningRuleName.BMI, 18.5, 23.9, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.HEART_RATE, 60.0, 100.0, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.TEMPERATURE, 36.0, 37.2, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.SYSTOLIC, 90.0, 140.0, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.DIASTOLIC, 60.0, 90.0, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.FASTING_GLUCOSE, 3.9, 6.1, 2, 0, true, null),
            new HealthWarningRule(null, HealthWarningRuleName.SPO2, 95.0, 100.0, 2, 0, true, null)
    );
    @Autowired
    private HealthWarningRuleMapper healthWarningRuleMapper;

    public List<HealthWarningRule> listBaseRules() {
        Integer userId = UserContext.getUserId();
        QueryWrapper<HealthWarningRule> wrapper = new QueryWrapper<HealthWarningRule>()
                .eq("type", HealthWarningType.BASIC)
                .eq("user_id", userId);
        List<HealthWarningRule> list = healthWarningRuleMapper.selectList(wrapper);
        if (list == null || list.size() == 0) {
            list = DEFAULT_RULES.stream().map(
                    rule -> {
                        rule.setUserId(userId);
                        return rule;
                    }
            ).toList();
            this.initDefaultRules(list);
        }
        return list;
    }

    private void initDefaultRules(List rules) {
        this.saveBatch(rules);
    }
}
