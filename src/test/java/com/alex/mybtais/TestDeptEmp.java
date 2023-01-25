package com.alex.mybtais;

import com.alex.mybatis.entity.Dept;
import com.alex.mybatis.entity.Emp;
import com.alex.mybatis.mapper.DeptMapper;
import com.alex.mybatis.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-25-17:31
 */
public class TestDeptEmp {


    public <T> T getMapper(Class<T> type ){
        InputStream is = null;
        T mapper =null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession = factory.openSession(true);
            mapper = sqlSession.getMapper(type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapper;

    }

    @Test
    public void testGetEmpByStep(){

        EmpMapper mapper = getMapper(EmpMapper.class);

        Emp emp = mapper.getEmpByStep1(1);

        System.out.println(emp);

    }

    @Test
    public void testGetDeptEmpById(){

        DeptMapper mapper = getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptEmpById(1);

        System.out.println(dept);

    }


    @Test
    public void testGetDeptEmpStep(){
        DeptMapper mapper = getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptEmpStep1(1);
        System.out.println(dept);

    }
}
