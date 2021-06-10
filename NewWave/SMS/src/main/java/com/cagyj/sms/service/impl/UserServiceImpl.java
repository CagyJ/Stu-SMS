package com.cagyj.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cagyj.sms.entity.User;
import com.cagyj.sms.exception.BussinessException;
import com.cagyj.sms.mapper.UserMapper;
import com.cagyj.sms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() == 0) {
            throw new BussinessException("E01", "no exists account");
        }
        if (users.size() == 1) {
            var oriUser = users.get(0);
            if (!user.getPassword().equals(oriUser.getPassword()) || oriUser == null) {
                throw new BussinessException("E01", "the password not match the account");
            }
            return user;
        }
        return null;
    }
}
