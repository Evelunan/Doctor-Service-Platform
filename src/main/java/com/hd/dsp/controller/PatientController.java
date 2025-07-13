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
        System.out.println(patientArchive);
        return Result.success(patientArchive);
    }

}
