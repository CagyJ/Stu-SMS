package com.cagy.controller;


import com.cagy.entity.Student;
import com.cagy.service.StudentService;
import com.cagy.service.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    StudentService service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. get method name
        String method = req.getParameter("method");
        if (method.equals("selectByPage")) {
            selectByPage(req, resp);
        } else if (method.equals("addStudent")) {
            addStudent(req, resp);
        } else if (method.equals("updateStudent")) {
            updateStudent(req, resp);
        } else if (method.equals("deleteStudent")) {
            deleteStudent(req, resp);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        String curPage = req.getParameter("curPage");
        String pageSize = req.getParameter("pageSize");
        String id = req.getParameter("id");
        service.deleteStudent(Integer.parseInt(id));
        // redirect to the original page
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&curPage="+curPage
                    +"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * update a student
     * @param req
     * @param resp
     */
    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) {
        var map = req.getParameterMap();
        var curPage = req.getParameter("curPage");
        String pageSize = req.getParameter("pageSize");

        Student student = new Student();

        // convert the date type
        dateConvert();

        // dependency injection
        try {
            BeanUtils.populate(student, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // call service add method
        service.updateStudent(student);

        // redirect to the original page
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&curPage="+curPage
                    +"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add new student
     * @param req
     * @param resp
     */
    private void addStudent(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        String curPage = req.getParameter("curPage");
        String pageSize = req.getParameter("pageSize");

        Student student = new Student();

        // convert the date type
        dateConvert();

        // dependency injection
        try {
            BeanUtils.populate(student, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // call service add method
        service.addStudent(student);

        // redirect to the original page
        try {
            resp.sendRedirect(req.getContextPath()+"/studentServlet?method=selectByPage&curPage="+curPage
                +"&pageSize="+pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dateConvert() {
        ConvertUtils.register(new Converter() {
              @Override
              public Object convert(Class type, Object value) {
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                  try {
                      return simpleDateFormat.parse(value.toString());
                  } catch (ParseException e) {
                      e.printStackTrace();
                  }
                  return null;
              }
          }, Date.class);
    }

    /**
     * pagination selection
     * @param req
     * @param resp
     */
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        String curPage = req.getParameter("curPage");
        String pageSize = req.getParameter("pageSize");

        // call select method from service
        Page page = service.selectByPage(Integer.parseInt(curPage), Integer.parseInt(pageSize));

        // encapsulate PageInfo
        PageInfo info = new PageInfo(page);

        // trans info to JSON format
        try {
            String json = new ObjectMapper().writeValueAsString(info);
            resp.getWriter().write(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
