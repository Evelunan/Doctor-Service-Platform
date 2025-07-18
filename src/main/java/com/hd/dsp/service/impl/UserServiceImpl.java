package com.hd.dsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hd.dsp.mapper.UserMapper;
import com.hd.dsp.pojo.FollowupPlan;
import com.hd.dsp.pojo.User;
import com.hd.dsp.pojo.vo.NoticeVO;
import com.hd.dsp.service.FollowupPlanService;
import com.hd.dsp.service.UserService;
import com.hd.dsp.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FollowupPlanService followupPlanService;

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
        user.setType(0);
        user.setUpdated(true);
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
    public boolean updatePassword(Integer id, String newPassword) {
        return userMapper.updatePassword(id, newPassword);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getElders(Integer id) {
        return userMapper.selectList(new QueryWrapper<User>().eq("doctor_id", id));
    }

    @Override
    public List<NoticeVO> getNotice(Integer elderId) {

        return userMapper.getNotice(elderId);
    }

}
