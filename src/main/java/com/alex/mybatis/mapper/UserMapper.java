package com.alex.mybatis.mapper;

import com.alex.mybatis.entity.Emp;
import com.alex.mybatis.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-24-22:08
 */
public interface UserMapper {

    /**
     * 添加用户信息
     */
    int insertUser();



    User getUserById(@Param("id1") int id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getUserList();


    /**
     * 查询用户的总记录数
     * @return
     * 在MyBatis中，对于Java中常用的类型都设置了类型别名
     * 例如： java.lang.Integer-->int|integer
     * 例如： int-->_int|_integer
     * 例如： Map-->map,List-->list
     */
    int getCount();

    /**
     * 根据用户id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String,Object> getUserToMap(@Param("id")int id);


    /**
     * 查询所有用户信息为map集合
     * @return
     * 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，此
    时可以将这些map放在一个list集合中获取
     */
    List<Map<String, Object>> getAllUserToMap();

    /**
     * 查询所有用户信息为map集合
     * @return
     * 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，并
    且最终要以一个map的方式返回数据，此时需要通过@MapKey注解设置map集合的键，值是每条数据所对应的
    map集合
     */
    @MapKey("id")
    Map<String,Object> getAllUserToMap2();

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<User> getMohu(@Param("keyword")String keyword);


    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteMore(@Param("ids") String ids);


    /**
     * useGeneratedKeys：设置使用自增的主键
     * keyProperty：因为增删改有统一的返回值是受影响的行数，因此只能将获取的自增的主键放在传输的参数user对象的某个属性中
     * @param user
     * @return
     */
    int insertUserGeneratedKeys(User user);



    Emp getEmpAndDeptByEid(@Param("id")int id);


    /**
     * 利用动态sql 批量插入数据
     */

    int insertBatch(@Param("users") List<User> users);


    /**
     * 分页查询
     */
   List<User> getUserLimitPage();
}
