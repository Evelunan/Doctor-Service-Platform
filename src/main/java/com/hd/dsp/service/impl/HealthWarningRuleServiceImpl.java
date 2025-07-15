package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.HealthWarningRuleMapper;
import com.hd.dsp.pojo.HealthWarningRule;
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
            new HealthWarningRule(null, "BMI", 18.5, 23.9, 2, 0, true, null),
            new HealthWarningRule(null, "心率", 60.0, 100.0, 2, 0, true, null),
            new HealthWarningRule(null, "体温", 36.0, 37.2, 2, 0, true, null),
            new HealthWarningRule(null, "收缩压", 90.0, 140.0, 2, 0, true, null),
            new HealthWarningRule(null, "舒张压", 60.0, 90.0, 2, 0, true, null),
            new HealthWarningRule(null, "空腹血糖", 3.9, 6.1, 2, 0, true, null),
            new HealthWarningRule(null, "血氧饱和度", 95.0, 100.0, 2, 0, true, null)
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
