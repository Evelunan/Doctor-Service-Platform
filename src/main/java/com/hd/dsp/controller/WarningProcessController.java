package com.hd.dsp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.WarningInfo;
import com.hd.dsp.pojo.vo.PageVO;
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
    public Result listWarningUsers( PageVO dto){
        warningProcessService.inspect();

        Integer doctorId = UserContext.getUserId();
        Page<WarningUserVO> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        page = warningProcessService.listWarningUsers(page, doctorId);
        PageVO vo = new PageVO<>();
        vo.setTotal(page.getTotal());
        vo.setList(page.getRecords());
        vo.setPageSize(page.getSize());
        vo.setPageNum(page.getCurrent());
        return Result.success(vo);
    }
    @GetMapping("/getWarningInfo/{userId}")
    public Result getWarningInfo(@PathVariable("userId") Integer userId, @RequestParam Long pageNum, @RequestParam Long pageSize){
        QueryWrapper<WarningInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patient_id", userId).orderByDesc("warning_time");
        Page<WarningInfo> page = new Page<>(pageNum, pageSize);
        page = warningProcessService.page(page, queryWrapper);

        PageVO<WarningInfo> pageVO = new PageVO<>();
        pageVO.setTotal(page.getTotal());
        pageVO.setList(page.getRecords());
        return Result.success(pageVO);
    }
    @PutMapping("/updateWarningInfo")
    public Result updateWarningInfo(@RequestBody WarningInfo warningInfo){
        System.out.println(warningInfo);
        warningInfo.setDoctorId(UserContext.getUserId());
        warningInfo.setStatus( true);
        return Result.success(warningProcessService.updateById(warningInfo));
    }
}
