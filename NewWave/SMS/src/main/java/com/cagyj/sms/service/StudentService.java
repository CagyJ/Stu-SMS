package com.cagyj.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.sms.entity.Student;


public interface StudentService {

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public IPage<Student> paging(Integer page, Integer rows);

    /**
     * add a new student
     * @param student
     * @return
     */
    public Student addStudent(Student student);

    /**
     * update info of a student
     * @param student
     * @return
     */
    public Student updateStudent(Student student);

    /**
     * delete a student
     * @param id
     */
    public void deleteStudent(Integer id);
}
