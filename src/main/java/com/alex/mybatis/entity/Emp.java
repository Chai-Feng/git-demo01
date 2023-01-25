package com.alex.mybatis.entity;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-25-15:33
 */
public class Emp{

    private Integer eid;
    private String ename;
    private Integer age;
    private String gender;
    private Dept dept;  //多对一映射

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        System.out.println("++++++++++");
       this.dept=dept;
    }



    @Override
    public String toString() {
        return "Emp{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                ", dept=" + dept +
                ", gender='" + gender + '\'' +
                '}';
    }



    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {

        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



}
