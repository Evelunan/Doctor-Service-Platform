package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.WarningInfoMapper;
import com.hd.dsp.pojo.*;
import com.hd.dsp.pojo.constant.HealthWarningRuleName;
import com.hd.dsp.pojo.constant.HealthWarningType;
import com.hd.dsp.pojo.vo.UserVo;
import com.hd.dsp.pojo.vo.WarningUserVO;
import com.hd.dsp.service.HealthWarningRuleService;
import com.hd.dsp.service.PatientService;
import com.hd.dsp.service.UserService;
import com.hd.dsp.service.WarningProcessService;
import com.hd.dsp.utils.UserContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarningProcessServiceImpl extends ServiceImpl<WarningInfoMapper, WarningInfo> implements WarningProcessService {
    private final WarningInfoMapper warningInfoMapper;
    private final PatientService patientService;
    private final UserService userService;
    private final HealthWarningRuleService ruleService;

    private List<User> updatedUser;

    private List<HealthWarningRule> basicRules;
    private List<HealthWarningRule> familyRules;
    private List<HealthWarningRule> diseaseRules;

    private List<WarningInfo> warningInfoList;

    public WarningProcessServiceImpl(WarningInfoMapper warningInfoMapper, PatientService patientService, UserService userService, HealthWarningRuleService ruleService) {
        this.warningInfoMapper = warningInfoMapper;
        this.patientService = patientService;
        this.userService = userService;
        this.ruleService = ruleService;
    }

    @Override
    public Page<WarningUserVO> listWarningUsers(Page<WarningUserVO> page, Integer doctorId) {
        if (UserContext.getUserType() == 0)
            return warningInfoMapper.listWarningUsers(page, doctorId);
        return warningInfoMapper.listWarningSelf(page, doctorId);
    }

    @Override
    public void inspect() {
        Integer currentUserId = UserContext.getUserId();
        initUpdateUser(currentUserId);
        initRules(currentUserId);
        System.out.println("检查用户" + currentUserId);
        System.out.println("开始检查预警");
        for (User user : updatedUser) {
            warningInfoList = new ArrayList<>();
            UserVo vo = patientService.getPatientArchive(user.getId());
            process(vo);
            user.setUpdated(false);
            userService.updateUser(user);
            this.saveBatch(warningInfoList);
        }
    }

    private void process(UserVo user) {
        if (user == null) {
            return;
        }
        processBasic(user.getHealthInfo());
        processFamily(user.getFamilyHistoryList());
        processDisease(user.getDiseaseHistoryList());
    }


    // 处理既往病史
    private void processDisease(List<DiseaseHistory> list) {
        LocalDate now = LocalDate.now();
        for (DiseaseHistory diseaseHistory : list) {
            // 康复状态直接跳过
            if (diseaseHistory.getStatus() == 1) {
                continue;
            }
            // 获取对应的疾病规则
            HealthWarningRule rule = diseaseRules.stream()
                    .filter(r -> r.getName().strip().equals(diseaseHistory.getDiseaseName().strip()))
                    .findFirst()
                    .orElse(null);
            if (rule == null) {
                continue;
            }

            // 计算最近复发时间到当前日期的天数差
            Double diff = (double) ChronoUnit.DAYS.between(diseaseHistory.getRelapseDate(), now);
//            System.out.println("既往病史天数差" + diff);
            if (diff <= rule.getLower()) {
                WarningInfo warningInfo = createWarningInfo(
                        diseaseHistory.getUserId(),
                        rule,
                        "最近复发",
                        null
                );
                warningInfoList.add(warningInfo);
            }
        }
    }

    // 处理家族病史
    private void processFamily(List<FamilyHistory> list) {
        LocalDate now = LocalDate.now();
        for (FamilyHistory familyHistory : list) {
            // 康复状态直接跳过
            if (familyHistory.getStatus() == 1) {
                continue;
            }
            // 获取对应的家族病史规则
            HealthWarningRule rule = familyRules.stream()
                    .filter(r -> r.getName().strip().equals(familyHistory.getDiseaseName().strip()))
                    .findFirst()
                    .orElse(null);
            if (rule == null) {
                continue;
            }

            // 计算最近复发时间到当前日期的天数差
            Double diff = (double) ChronoUnit.DAYS.between(familyHistory.getRelapseDate(), now);
//            System.out.println("家族病史:" + diff );
            if (diff <= rule.getLower()) {
                WarningInfo warningInfo = createWarningInfo(
                        familyHistory.getUserId(),
                        rule,
                        "最近复发（家族病）",
                        null
                );
                warningInfoList.add(warningInfo);
            }
        }
    }


    private void processBasic(HealthInfo info) {
        if (info == null) {
            return;
        }
        for (HealthWarningRule rule : basicRules) {
            if (rule == null || rule.getName() == null || !rule.getEnabled()) {
                continue;
            }

            Double value = getValueByRule(info, rule.getName());
            if (value == null) {
                continue;
            }

            if (value > rule.getUpper()) {
                WarningInfo warningInfo = createWarningInfo(info.getUserId(), rule, "超过", rule.getUpper());
                warningInfoList.add(warningInfo);
            } else if (value < rule.getLower()) {
                WarningInfo warningInfo = createWarningInfo(info.getUserId(), rule, "低于", rule.getLower());
                warningInfoList.add(warningInfo);
            }
        }

    }

    private Double getValueByRule(HealthInfo info, String ruleName) {
        switch (ruleName) {
            case HealthWarningRuleName.BMI:
                if (info.getHeight() == null || info.getWeight() == null) {
                    return null;
                }
                return info.getWeight() / Math.pow(info.getHeight(), 2);
            case HealthWarningRuleName.HEART_RATE:
                return info.getHeartRate();
            case HealthWarningRuleName.TEMPERATURE:
                return info.getTemperature();
            case HealthWarningRuleName.SYSTOLIC:
                return info.getSystolic();
            case HealthWarningRuleName.DIASTOLIC:
                return info.getDiastolic();
            case HealthWarningRuleName.FASTING_GLUCOSE:
                return info.getFastingGlucose();
            case HealthWarningRuleName.SPO2:
                return info.getSpo2();
            default:
                return null;
        }
    }

    private WarningInfo createWarningInfo(Integer patientId, HealthWarningRule rule, String compareType, Double threshold) {
        WarningInfo warningInfo = new WarningInfo();
        warningInfo.setName(rule.getName());
        warningInfo.setLevel(rule.getLevel());
        if (threshold != null) {
            warningInfo.setDesc(rule.getName() + compareType + threshold);
        }
        warningInfo.setDesc(rule.getName() + compareType);
        warningInfo.setWarningTime(LocalDateTime.now());
        warningInfo.setStatus(false); // 默认未处理
        warningInfo.setPatientId(patientId);
        return warningInfo;
    }

    private void initUpdateUser(Integer doctorId) {
        QueryWrapper<User> userWrapper = new QueryWrapper<User>()
                .eq("updated", true)
                .eq("doctor_id", doctorId);
        updatedUser = userService.list(userWrapper);
    }

    private void initRules(Integer userId) {
        QueryWrapper<HealthWarningRule> ruleWrapper = new QueryWrapper<>();
        ruleWrapper
                .eq("enabled", true)
                .eq("user_id", userId);
        List<HealthWarningRule> rules = ruleService.list(ruleWrapper);

        Map<Integer, List<HealthWarningRule>> rulesByType = rules.stream()
                .collect(Collectors.groupingBy(HealthWarningRule::getType));

        basicRules = rulesByType.getOrDefault(HealthWarningType.BASIC, Collections.emptyList());
        diseaseRules = rulesByType.getOrDefault(HealthWarningType.DISEASE_HISTORY, Collections.emptyList());
        familyRules = rulesByType.getOrDefault(HealthWarningType.FAMILY_HISTORY, Collections.emptyList());
    }
}
