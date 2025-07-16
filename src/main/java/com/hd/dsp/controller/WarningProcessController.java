package com.hd.dsp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.WarningUserVO;
import com.hd.dsp.service.WarningProcessService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warningProcess")
public class WarningProcessController {

    @Autowired
    WarningProcessService warningProcessService;

    @GetMapping("/listWarningUsers")
    public Result listWarningUsers(){
        Integer doctorId = UserContext.getUserId();
        List<WarningUserVO> list = warningProcessService.listWarningUsers(doctorId);

        return Result.success(list);
    }
    @GetMapping("/getWarningInfo/{userId}")
    public Result getWarningInfo(@PathVariable("userId") Integer userId){
        QueryWrapper<WarningInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patient_id", userId).orderByDesc("warning_time");
        List<WarningInfo> list = warningProcessService.list(queryWrapper);
        return Result.success(list);
    }
    @PutMapping("/updateWarningInfo")
    public Result updateWarningInfo(@RequestBody WarningInfo warningInfo){
        System.out.println(warningInfo);
        warningInfo.setDoctorId(UserContext.getUserId());
        warningInfo.setStatus( true);
        return Result.success(warningProcessService.updateById(warningInfo));
    }
}
