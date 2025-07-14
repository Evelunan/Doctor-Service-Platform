package com.hd.dsp.controller;

import com.hd.dsp.pojo.FollowupHistory;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.service.FollowupHistoryService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/followupHistory")
public class FollowupHistoryController {
    @Autowired
    private FollowupHistoryService followupHistoryService;

    @PostMapping("/add")
    public Result addFollowupHistory(@RequestBody FollowupHistory followupHistory){
        if(UserContext.getUserId()==null){
            return Result.error("用户未登录");
        }

        return Result.success(followupHistoryService.save(followupHistory));
    }
}
