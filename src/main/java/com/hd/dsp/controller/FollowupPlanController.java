package com.hd.dsp.controller;

import com.hd.dsp.pojo.FollowupPlan;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.vo.NoticeVO;
import com.hd.dsp.service.FollowupPlanService;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/followupPlan")
public class FollowupPlanController {
    @Autowired
    private FollowupPlanService followUpPlanService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result addFollowUpPlan(@RequestBody FollowupPlan followUpPlan){
        if(UserContext.getUserId()==null){
            return Result.error("用户未登录");
        }
        followUpPlan.setDoctorId(UserContext.getUserId());
        return Result.success(followUpPlanService.save(followUpPlan));
    }

    @GetMapping("/list")
    public Result listFollowUpPlan(){
        if(UserContext.getUserType()==0){
            Integer doctor_id = UserContext.getUserId();
            return Result.success(followUpPlanService.getPlanList(doctor_id));
        }
        else{
            Integer elder_id = UserContext.getUserId();
            return Result.success(followUpPlanService.getElders(elder_id));
        }
    }

    @GetMapping("/getNotice")
    public Result getNotice(){
        Integer elder_id = UserContext.getUserId();
        return Result.success(userService.getNotice(elder_id));
    }
    
    @GetMapping("/get/{id}")
    public Result getOneFollowUpPlan(@PathVariable("id") Integer id){
        return Result.success(followUpPlanService.getById(id));
    }

    @GetMapping("/getCompleteList")
    public Result getFollowUpPlans(){
        Integer doctor_id = UserContext.getUserId();
        return Result.success(followUpPlanService.getCompleteList(doctor_id));
    }

    @PutMapping("/update")
    public Result updateFollowUpPlan(@RequestBody FollowupPlan followUpPlan){
        if(UserContext.getUserId()==null){
            return Result.error("用户未登录");
        }
        followUpPlan.setDoctorId(UserContext.getUserId());
        return Result.success(followUpPlanService.updateById(followUpPlan));
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteFollowUpPlan(@PathVariable("id") Integer id){
        return Result.success(followUpPlanService.removeById(id));
    }
}
