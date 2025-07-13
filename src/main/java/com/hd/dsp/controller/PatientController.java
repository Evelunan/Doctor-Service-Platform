package com.hd.dsp.controller;

import com.hd.dsp.pojo.Result;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    Result patientList() {
        Integer doctor_id = UserContext.getUserId();
        return Result.success(userService.getElders(doctor_id));
    }
}
