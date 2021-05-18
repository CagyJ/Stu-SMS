package com.cagy.service;

import com.cagy.entity.Student;
import com.cagy.mapper.StudentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> selectStudent(String status) {
        InputStream is = null;
        SqlSession sqlSession = null;
        List<Student> list = null;
        try {
            // 1. load core configuration
            is = Resources.getResourceAsStream("MyBatisConfig.xml");

            // 2. get SqlSession factory object
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            // 3. get SqlSession object
            sqlSession = factory.openSession(true);

            // 4. get the object of UserMapper
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            // 5. call login method
            list = mapper.selectStudent(status);


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

    @Override
    public Page selectByPage(Integer curPage, Integer pageSize) {
        InputStream is = null;
        SqlSession sqlSession = null;
        Page page = null;
        try {
            // 1. load core config file
            is = Resources.getResourceAsStream("MyBatisConfig.xml");
            // 2. get SqlSession factory instance
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 3. get SqlSession instance from  SqlSessionFactory
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. get StudentMapper interface
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. set pagination parameters
            page = PageHelper.startPage(curPage, pageSize);
            // 6. call the method
            mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7. release resources
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
        return page;
    }

    @Override
    public void addStudent(Student student) {
        InputStream is = null;
        SqlSession sqlSession = null;
        try {
            // 1. load core config file
            is = Resources.getResourceAsStream("MyBatisConfig.xml");
            // 2. get SqlSession factory instance
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 3. get SqlSession instance from  SqlSessionFactory
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. get StudentMapper interface
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. call the method
            mapper.addStudent(student);
        } catch (IOException e) {
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
    }

    @Override
    public void updateStudent(Student student) {
        InputStream is = null;
        SqlSession sqlSession = null;
        try {
            // 1. load core config file
            is = Resources.getResourceAsStream("MyBatisConfig.xml");
            // 2. get SqlSession factory instance
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 3. get SqlSession instance from  SqlSessionFactory
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. get StudentMapper interface
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. call the method
            mapper.updateStudent(student);
        } catch (IOException e) {
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
    }

    @Override
    public void deleteStudent(int id) {
        InputStream is = null;
        SqlSession sqlSession = null;
        try {
            // 1. load core config file
            is = Resources.getResourceAsStream("MyBatisConfig.xml");
            // 2. get SqlSession factory instance
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 3. get SqlSession instance from  SqlSessionFactory
            sqlSession = sqlSessionFactory.openSession(true);
            // 4. get StudentMapper interface
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            // 5. call the method
            mapper.deleteStudent(id);
        } catch (IOException e) {
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
    }
}
