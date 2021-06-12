package com.cagyj.sms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.sms.entity.Student;
import com.cagyj.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class StudentsController {

    @Resource
    private StudentService studentService;

    @PostMapping("/listStudents")
    @ResponseBody
    public IPage<Student> listStudents(@RequestParam Integer page, @RequestParam("rows") Integer rows) {
        System.out.println("========" + page + ": " + rows);
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 5;
        }
        IPage<Student> paging = studentService.paging(page, rows);
        return paging;
    }
}
