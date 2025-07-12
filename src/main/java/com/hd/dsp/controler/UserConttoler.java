package com.hd.dsp.controler;

import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.User;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.JwtUtil;
import com.hd.dsp.utils.Md5Util;
import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserConttoler {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @Validated
    public Result regist(@RequestParam String account, @RequestParam String password){
        if(userService.findByAccount(account)){
            return Result.error("用户已存在");
        }
        else{
            userService.regist(account, password);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam String account, @RequestParam String password){
        User user = userService.getUserByAccount(account);
        if(user != null){
            userService.login(account, password);
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", account);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("用户不存在或密码错误");
    }


    @PutMapping("/update")
    public Result update(@RequestParam String newPassword) {
        User user = userService.getUserByAccount("admin");
        if(StringUtils.isEmpty(newPassword)){
            return Result.error("密码不能为空！");
        }
        String md5Password = Md5Util.getMD5String(newPassword);
        if(md5Password.equals(user.getPassword())){
            return Result.error("当前密码与原密码一致！");
        }
        if(userService.update(user.getId(), newPassword)){
            return Result.success();
        }
        return Result.error("操作失败");
    }

    @GetMapping("/getDoctor/{id}")
    public Result getDoctor(@PathVariable("id") Integer  id){
//        User user = userService.getUserByAccount("admin");
        System.out.println("id:"+id);
        return Result.success(userService.getById(id));
    }

    @PutMapping("/updateDoctor")
    public Result updateDoctor(@RequestBody User user) {
        if(userService.updateDoctor(user)>0){
            return Result.success();
        }
        return Result.error("操作失误！");
    }
}
