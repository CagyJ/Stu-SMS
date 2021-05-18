package com.cagy.mapper;

import com.cagy.entity.Student;
import com.cagy.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    /**
     * load the data of student
     * @param status
     * @return
     */
    @Select("SELECT * FROM student WHERE status=#{status}")
    public abstract List<Student> selectStudent(String status);


    /**
     * select all data from student db
     * @return
     */
    @Select("SELECT * FROM student")
    public abstract List<Student> selectAll();

    /**
     * insert a new data of a student
     * @param student
     */
    @Insert("INSERT INTO student VALUES (#{id}, #{name}, #{sex}, #{date}, #{status})")
    public abstract void addStudent(Student student);

    /**
     * update a date of student
     * @param stu
     */
    @Update("UPDATE student SET id=#{id}, name=#{name}, sex=#{sex}, date=#{date}, status=#{status} WHERE id=#{id}")
    public abstract void updateStudent(Student stu);

    /**
     * delete a student by id
     */
    @Delete("DELETE FROM student WHERE id=#{id}")
    public abstract void deleteStudent(Integer id);
}
