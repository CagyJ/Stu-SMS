package com.cagy.service;

import com.cagy.entity.Student;
import com.github.pagehelper.Page;

import java.util.List;

public interface StudentService {

    public abstract List<Student> selectStudent(String status);

    public abstract Page selectByPage(Integer curPage, Integer pageSize);

    public abstract void addStudent(Student student);

    public abstract void updateStudent(Student student);

    public abstract void deleteStudent(int id);
}
