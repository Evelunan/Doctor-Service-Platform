package com.hd.dsp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.User;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.JwtUtil;
import com.hd.dsp.utils.Md5Util;
import com.hd.dsp.utils.UserContext;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Validated
    public Result regist(@RequestParam String account, @RequestParam String password) {
        if (userService.findByAccount(account)) {
            return Result.error("用户已存在");
        } else {
            userService.regist(account, password);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam String account, @RequestParam String password) {
        User user = userService.getUserByAccount(account);
        if (user != null) {
            System.out.println(password);
            if(userService.login(account, password)){
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("type", user.getType());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            }
        }
        return Result.error("用户不存在或密码错误");
    }


    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestParam String newPassword) {
        Integer userId = UserContext.getUserId();
        User user = userService.getById(userId);
        if (StringUtils.isEmpty(newPassword)) {
            return Result.error("密码不能为空！");
        }
        String md5Password = Md5Util.getMD5String(newPassword);
        if (md5Password.equals(user.getPassword())) {
            return Result.error("当前密码与原密码一致！");
        }
        if (userService.updatePassword(user.getId(), md5Password)) {
            return Result.success();
        }
        return Result.error("操作失败");
    }

    @GetMapping("/getUser/{id}")
    public Result getUser(@PathVariable("id") Integer id) {
        return Result.success(userService.getById(id));
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        if (userService.updateUser(user) > 0) {
            return Result.success();
        }
        return Result.error("操作失误！");
    }

    @PostMapping("/createElder/{id}")
    public Result createElder(@PathVariable("id") Integer id, @RequestBody User user) {
        if (userService.findByAccount(user.getAccount())) {
            return Result.error("用户已存在");
        }
        String pd = user.getPassword();

        if (StringUtils.isEmpty(pd)) {
            return Result.error("密码不能为空！");
        }
        pd = Md5Util.getMD5String(pd);
        user.setPassword(pd);
        user.setDoctorId(id); //指定医生为当前用户
        user.setType(1); //老人
        userService.insert(user);
        return Result.success();
    }

    @PutMapping("/updateElder")
    public Result updateElder(@RequestBody User user) {
        if (userService.updateUser(user) > 0) {
            return Result.success();
        }
        return Result.error("操作失误！");
    }

    @DeleteMapping("/deleteElder/{id}")
    public Result deleteElder(@PathVariable("id") Integer id) {
        if (userService.removeById(id)) {
            return Result.success();
        }
        return Result.error("操作失误！");
    }

    // 查询当前医生的所有老人
    @GetMapping("/getElders")
    public Result getElder() {
        if (UserContext.getUserId() == null) {
            return Result.error("用户未登录");
        }
        return Result.success(userService.getElders(UserContext.getUserId()));
    }

    @GetMapping("/getCurrentUser")
    public Result getCurrentUser() {
        Integer userId = UserContext.getUserId();
        if (userId != null) {
            return Result.success(userService.getById(userId));
        } else {
            return Result.error("用户未登录");
        }
    }

    @GetMapping("/getAllDoctor")
    public Result getAllDoctor() {
        List<User> doctors = userService.list(new QueryWrapper<User>().eq("type", 0));
        return Result.success(doctors);
    }
}

