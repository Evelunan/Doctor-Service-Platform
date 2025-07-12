package com.hd.dsp.service;

import com.hd.dsp.pojo.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public interface UserService {

    boolean findByAccount(String account);

    void regist(String account, String password);

    boolean login(@Pattern(regexp = "^\\S{5,10}$") String account, @Pattern(regexp = "^\\S{5,10}$") String password);

    User getUserByAccount(String account);

    boolean update(@NotNull Integer id, String newPassword);

    int updateDoctor(User user);
}
