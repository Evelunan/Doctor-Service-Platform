package com.hd.dsp.controler;

import com.hd.dsp.pojo.Result;
import com.hd.dsp.pojo.User;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(account+ password);
        return Result.error("用户不存在或密码错误");
    }
}
