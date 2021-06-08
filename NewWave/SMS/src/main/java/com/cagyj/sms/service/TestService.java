package com.cagyj.sms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cagyj.sms.entity.Test;
import com.cagyj.sms.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public void select() {
        QueryWrapper<Test> queryWrapper = new QueryWrapper<Test>();
        queryWrapper.ge("id", 1);
        List<Test> list = testMapper.selectList(queryWrapper);
        list.stream().forEach(System.out::println);
    }
}
