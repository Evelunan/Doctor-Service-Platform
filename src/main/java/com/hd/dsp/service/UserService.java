package com.hd.dsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hd.dsp.pojo.User;
import com.hd.dsp.pojo.vo.NoticeVO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public interface UserService extends IService<User> {

    boolean findByAccount(String account);

    void regist(String account, String password);

    boolean login(@Pattern(regexp = "^\\S{5,10}$") String account, @Pattern(regexp = "^\\S{5,10}$") String password);

    User getUserByAccount(String account);

    boolean updatePassword(@NotNull Integer id, String newPassword);

    int updateUser(User user);

    void insert(User user);

    List<User> getElders(Integer id);

    List<NoticeVO> getNotice(Integer elderId);
}
