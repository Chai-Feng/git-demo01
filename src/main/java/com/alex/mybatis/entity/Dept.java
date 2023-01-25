package com.alex.mybatis.entity;

import java.util.List;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-25-15:36
 */
public class Dept {
    private Integer did;
    private String dname;
    private List<Emp> emps;  //多对一映射   一个部门可以有多个员工

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", emps=" + emps +
                '}';
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }



    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

}
