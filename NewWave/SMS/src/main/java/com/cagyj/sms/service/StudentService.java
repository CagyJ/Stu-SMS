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
}
