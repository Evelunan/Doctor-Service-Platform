package com.hd.dsp.controller;

import com.hd.dsp.pojo.Result;
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
    Result patientList() {
        Integer doctor_id = UserContext.getUserId();
        return Result.success(userService.getElders(doctor_id));
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
