package com.cagy.service;

import com.cagy.entity.User;

import java.util.List;

/*
    Service layer interface
 */
public interface UserService {
    /*
        login method
     */
    public abstract List<User> login(User user);
}
