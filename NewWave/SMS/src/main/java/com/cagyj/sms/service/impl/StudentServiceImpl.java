package com.cagyj.sms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cagyj.sms.entity.Student;
import com.cagyj.sms.exception.BussinessException;
import com.cagyj.sms.mapper.StudentMapper;
import com.cagyj.sms.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("studentService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;


    @Override
    public IPage<Student> paging(Integer page, Integer rows) {
        var p = new Page<Student>(page, rows);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        Page<Student> studentPage = studentMapper.selectPage(p, queryWrapper);
        return studentPage;
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        try {
            studentMapper.insert(student);
        } catch (Exception e) {
            throw new BussinessException("C01", "Insert failure.");
        }
        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        Student s = null;
        try {
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("id", student.getId());
            s = studentMapper.selectOne(studentQueryWrapper);
            s.setDate(student.getDate());
            s.setName(student.getName());
            s.setSex(student.getSex());
            s.setStatus(student.getStatus());
            studentMapper.updateById(s);
        } catch (Exception e) {
            throw new BussinessException("CO1", "UPDATE failure");
        }
        return s;
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        try {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            studentMapper.delete(queryWrapper);
        } catch (Exception e) {
            throw new BussinessException("C01", "DELETE failure");
        }
    }
}
