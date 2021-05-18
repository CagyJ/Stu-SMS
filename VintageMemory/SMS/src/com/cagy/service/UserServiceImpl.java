package com.cagy.service;

import com.cagy.entity.User;
import com.cagy.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public List<User> login(User user) {
        InputStream is = null;
        SqlSession sqlSession = null;
        List<User> list = null;
        try {
            // 1. load core configuration
            is = Resources.getResourceAsStream("MyBatisConfig.xml");

            // 2. get SqlSession factory object
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            // 3. get SqlSession object
            sqlSession = factory.openSession(true);

            // 4. get the object of UserMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            // 5. call login method
            list = mapper.login(user);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. release resources
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        // 7. return to CONTROLLER
        return list;
    }
}
