package com.alex.mybatis.mapper;

import com.alex.mybatis.entity.Dept;
import com.alex.mybatis.entity.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-25-17:28
 */
public interface EmpMapper {

    /**
     * 通过分步查询查询员工信息
     *     分步查询:查询员工以及所在部门信息的第一步
     * @param eid
     * @return
     */
    Emp getEmpByStep1(@Param("eid") int eid);

    /**
     * 分步查询的第二步： 根据员工所对应的dept_id 查询部门信息
     * @param did
     * @return
     */
    Dept getEmpDeptByStep2(@Param("did") int did);

//-----------------------------------------------------------------------
    /**
     *
     * 通过分步查询 部门的具体信息(该部门的所有信息包括员工信息)
     */
    Emp getDeptEmpStep2(@Param("did") int did);

}
