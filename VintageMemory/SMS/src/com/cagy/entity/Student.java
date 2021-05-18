package com.cagy.entity;

import java.util.Date;

public class Student {

    private Integer id;
    private String name;
    private String sex;
    private Date date;
    private String status;

    public Student(Integer id, String name, String sex, Date date, String status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.status = status;
    }

    public Student() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
