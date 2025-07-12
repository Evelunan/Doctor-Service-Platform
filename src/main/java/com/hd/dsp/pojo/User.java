package com.hd.dsp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer userId;//主键ID
    private String account;//用户名
    @JsonIgnore//让springmvc把当前对象转换成json字符串的时候,忽略password,最终的json字符串中就没有password这个属性了
    private String password;//密码

    @Pattern(regexp = "^\\S{1,10}$")
    private String username;//昵称

    @Pattern(regexp = "^\\S[0,1]$")
    private Integer gender; //0男 1女
    private String address;

    private LocalDateTime birthdate;

    private String phone;

    private Integer doctorId;

}
