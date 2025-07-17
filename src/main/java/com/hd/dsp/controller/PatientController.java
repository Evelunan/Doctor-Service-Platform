package com.hd.dsp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.User;
import com.hd.dsp.pojo.vo.PageVO;
import com.hd.dsp.pojo.vo.UserVo;
import com.hd.dsp.service.PatientService;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;
    @GetMapping("/list")
    Result patientList(PageVO dto) {
        Integer doctor_id = UserContext.getUserId();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 类型为医生医生时，查医生下的病人
        if (UserContext.getUserType() == 0){
            queryWrapper.eq("doctor_id", doctor_id);
        } else {
//            为病人时，查自己
            queryWrapper.eq("id", doctor_id);
        }
        Page<User> page = userService.page(new Page<>(dto.getPageNum(), dto.getPageSize()), queryWrapper);
        PageVO<User> pageVO = new PageVO<>();
        pageVO.setTotal(page.getTotal());
        pageVO.setList(page.getRecords());
        pageVO.setPageNum(dto.getPageNum());
        pageVO.setPageSize(dto.getPageSize());
        return Result.success(pageVO);
    }
    @GetMapping("/archive/{id}")
    Result archive(@PathVariable("id") Integer id) {
        UserVo patientArchive = patientService.getPatientArchive(id);
        return Result.success(patientArchive);
    }

    @PostMapping("/archive/add")
    Result addPatient(@RequestBody UserVo userVo) {
        System.out.println(userVo);
        patientService.addPatient(userVo);
        return Result.success();
    }
    @PutMapping("/archive/update")
    Result updatePatient(@RequestBody UserVo userVo) {
        patientService.updatePatient(userVo);
        return Result.success();
    }
    @DeleteMapping("/archive/delete/{id}")
    Result deletePatient(@PathVariable("id") Integer id) {
        patientService.deletePatient(id);
        return Result.success();
    }
}
