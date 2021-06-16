package com.cagyj.sms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.sms.entity.Student;
import com.cagyj.sms.exception.BussinessException;
import com.cagyj.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/list")
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


    @PostMapping("/addStudent")
    @ResponseBody
    public Map addNewStudent(Student student, Integer curPage, Integer pageSize, RedirectAttributes attr) {
        System.out.println("========" + student + curPage + pageSize);
        Map result = new HashMap();
        try {
            studentService.addStudent(student);
            result.put("code", "1");
            result.put("msg", "success");
        } catch (BussinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateStudent")
    @ResponseBody
    public Map updateStudent(Student student) {
        Map result = new HashMap();

        try {
            studentService.updateStudent(student);
            result.put("code", "1");
            result.put("msg", "success");
        } catch (BussinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/deleteStudent")
    @ResponseBody
    public Map deleteStudent(Integer id) {
        Map result = new HashMap();

        try {
            studentService.deleteStudent(id);
            result.put("code", "1");
            result.put("msg", "success");
        } catch (BussinessException e) {
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
            e.printStackTrace();
        }
        return result;
    }
}
