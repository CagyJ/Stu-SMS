package com.cagy.controller;


import com.cagy.entity.User;
import com.cagy.service.UserService;
import com.cagy.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 1. get the data of request
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. encapsulate a User object
        User user = new User(username, password);

        // 3. process the login from UserService
        List<User> list = service.login(user);

        // 4. determine the account
        if (list.size() != 0) {
            // store the username into session
            req.getSession().setAttribute("username", username);
            // response to client
            resp.getWriter().write("true");
        } else {
            // response to client
            resp.getWriter().write("false");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
