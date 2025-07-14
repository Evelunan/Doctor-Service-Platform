package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.FollowupPlan;

import java.util.List;

public interface FollowupPlanService extends IService<FollowupPlan> {
    List<FollowupPlan> getPlanList(Integer doctorId);

    List<FollowupPlan> getCompleteList(Integer doctorId);
}
