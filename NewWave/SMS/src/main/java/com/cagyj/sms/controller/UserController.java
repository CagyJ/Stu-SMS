package com.cagyj.sms.controller;

import com.cagyj.sms.entity.User;
import com.cagyj.sms.exception.BussinessException;
import com.cagyj.sms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller

public class UserController {

    @Resource
    private UserService userService;



    @PostMapping("/userLogin")
    @ResponseBody
    public Map login(@RequestBody User user, HttpServletRequest req) {
//        System.out.println("=====" + user.getUsername() + ": " + user.getPassword());
        Map result = new HashMap();
        try {
            User login = userService.login(user);
            req.getSession().setAttribute("username", login.getUsername());
            result.put("code", "1");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping("/userLogout")
    @ResponseBody
    public Map logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map result = new HashMap();
        System.out.println("Before remove: " + req.getSession().getAttribute("username"));
        if (req.getSession().getAttribute("username") != null) {
            req.setAttribute("username", req.getSession().getAttribute("username"));
            req.getSession().removeAttribute("username");
//            resp.sendRedirect("/login.html");
        }
        System.out.println("After remove: " + req.getSession().getAttribute("username"));
        result.put("code", "1");
        result.put("msg", "success");
        return result;
    }
}
