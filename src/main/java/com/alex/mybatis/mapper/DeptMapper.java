package com.alex.mybatis.mapper;

import com.alex.mybatis.entity.Dept;
import com.alex.mybatis.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-25-19:57
 */
public interface DeptMapper {

    /**
     *  根据部门id查新部门以及部门中的员工信息
     * * @param did
     * * @return
     */

    Dept getDeptEmpById(@Param("did") int did);


    /**
     * 分步查询1： 根据did 查询部门信息
     */

    Dept getDeptEmpStep1(@Param("did") int did);
}
