package com.cagyj.sms.controller;

import com.cagyj.sms.entity.User;
import com.cagyj.sms.exception.BussinessException;
import com.cagyj.sms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller

public class UserController {

    @Resource
    private UserService userService;



    @PostMapping("/userLogin")
    @ResponseBody
    public Map login(@RequestBody User user) {
//        System.out.println("=====" + user.getUsername() + ": " + user.getPassword());
        Map result = new HashMap();
        try {
            userService.login(user);
            result.put("code", "1");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
            ex.printStackTrace();
        }
        return result;
    }
}
