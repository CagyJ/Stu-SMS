package com.cagy.mapper;

import com.cagy.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /*
    Login method
     */
    @Select("SELECT SQL_NO_CACHE * FROM user WHERE username=#{username} AND password=#{password}")
    public abstract List<User> login(User user);
}
