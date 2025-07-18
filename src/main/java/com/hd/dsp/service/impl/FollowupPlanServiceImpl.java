package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.FollowUpPlanMapper;
import com.hd.dsp.pojo.FollowupPlan;
import com.hd.dsp.pojo.User;
import com.hd.dsp.service.FollowupPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowupPlanServiceImpl extends ServiceImpl<FollowUpPlanMapper, FollowupPlan> implements FollowupPlanService {
    @Autowired
    private FollowUpPlanMapper followUpPlanMapper;
    @Override
    public List<FollowupPlan> getPlanList(Integer doctorId) {
        return followUpPlanMapper.selectList(new QueryWrapper<FollowupPlan>().eq("doctor_id", doctorId));
    }

    @Override
    public List<FollowupPlan> getCompleteList(Integer doctorId) {
        return followUpPlanMapper.selectList(new QueryWrapper<FollowupPlan>().eq("doctor_id", doctorId).eq("status" ,2));
    }

    @Override
    public List<FollowupPlan> getElders(Integer elderId) {
        return followUpPlanMapper.selectList(new QueryWrapper<FollowupPlan>().eq("elder_id", elderId));
    }

    @Override
    public List<FollowupPlan> getByElderId(Integer elderId) {
        return followUpPlanMapper.selectList(new QueryWrapper<FollowupPlan>().eq("elder_id", elderId));
    }
}
