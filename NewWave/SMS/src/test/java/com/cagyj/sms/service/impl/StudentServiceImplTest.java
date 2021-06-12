package com.cagyj.sms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.sms.entity.Student;
import com.cagyj.sms.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentServiceImplTest {
    @Resource
    private StudentService studentService;

    @Test
    public void paging() {
        IPage<Student> paging = studentService.paging(1, 4);
        List<Student> records = paging.getRecords();
        records.forEach(System.out::println);
    }
}