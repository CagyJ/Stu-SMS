package com.cagy.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userProcess")
public class UserProcessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("logout")) {
            logout(req, resp);
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("username") != null) {
            req.setAttribute("username", req.getParameter("username"));
            req.getSession().removeAttribute("username");
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
