package com.cagy;

import com.cagy.entity.Student;
import com.cagy.service.StudentService;
import com.cagy.service.StudentServiceImpl;
import com.github.pagehelper.Page;
import org.junit.Test;

import java.util.List;

public class Tester {

    @Test
    public void testSelectStudent() {
        StudentServiceImpl studentService = new StudentServiceImpl();
        List<Student> list = studentService.selectStudent("active");
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectAll() {
        StudentService studentService = new StudentServiceImpl();
        Page page = studentService.selectByPage(1, 5);
        System.out.println(page);
    }

    public static void main(String[] args) {

    }
}
