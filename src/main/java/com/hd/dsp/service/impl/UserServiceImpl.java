package com.hd.dsp.service.impl;

import com.hd.dsp.mapper.UserMapper;
import com.hd.dsp.pojo.User;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean findByAccount(String account) {
        Map map = new HashMap();
        map.put("account", account);
        List list = userMapper.selectByMap(map);
        if(list.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public void regist(String account, String password) {
        String md5String = Md5Util.getMD5String(password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(md5String);
        userMapper.insert( user);
    }

    @Override
    public boolean login(String account, String password) {
        Map map = new HashMap();
        map.put("account", account);
        List<User> list = userMapper.selectByMap(map);
        if(Md5Util.getMD5String( password).equals(list.get(0).getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByAccount(String account) {
        Map map = new HashMap();
        map.put("account", account);
        List<User> list = userMapper.selectByMap(map);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean update(Integer id, String newPassword) {
        return userMapper.updatePassword(id, newPassword);
    }

    @Override
    public int updateDoctor(User user) {
        return userMapper.updateById(user);
    }
}
