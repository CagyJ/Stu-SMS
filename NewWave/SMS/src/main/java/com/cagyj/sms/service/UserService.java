package com.cagyj.sms.service;

import com.cagyj.sms.entity.User;

public interface UserService {

    /**
     * 登录操作
     * @param user
     * @return
     */
    public User login(User user);
}
