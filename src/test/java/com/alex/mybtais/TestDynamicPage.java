package com.alex.mybtais;

import com.alex.mybatis.entity.User;
import com.alex.mybatis.mapper.UserMapper;
import com.alex.mybtais.utils.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-26-15:18
 */
public class TestDynamicPage {
    //动态Sql 批量插入数据，为分页测试做准备
    @Test
    public void testInsertBatch(){
        UserMapper mapper = SqlSessionUtil.getMapper(UserMapper.class);
        List<User> users = new ArrayList<>();

        for(int i=0;i<100;i++){
            User user = new User("user"+i //username
                    , UUID.randomUUID().toString().substring(0,6) // password
                    ,i //age
                    ,i%2==0?"男":"女" //gender
                    ,i+"@gmail.com"); //email
            users.add(user);
        }
        int i = mapper.insertBatch(users);
        System.out.println("添加了 "+i+" 行记录");
    }


    /**
     * 测试密码生成器
     */
    @Test
    public void testRandom(){
        //方案1
        String str = UUID.randomUUID().toString().substring(0, 7);
        System.out.println(str);

        //方案2
        String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer flag = new StringBuffer();
        for(int j=0;j<6;j++){
            flag.append(source.charAt(random.nextInt(62))); //随机数产生10以内的任何一个数字，代表stringbuff下标
        }
        System.out.println(flag.toString());
    }

    /**
     * 分页测试
     */
    @Test
    public void testLimitPages(){
        UserMapper mapper = SqlSessionUtil.getMapper(UserMapper.class);
        PageHelper.startPage(2,10);
        List<User> users = mapper.getUserLimitPage();
        System.out.println(users);

        PageInfo<User> pageInfo = new PageInfo<>(users, 5);
        System.out.println(pageInfo);

        //------回忆一下----
        ArrayList<Integer> list1 = null;
        ArrayList<Integer> list2 = new ArrayList<>(); //已经在堆中划分地址空间 Default initial capacity :DEFAULT_CAPACITY =10

        System.out.println(list1+" \n"+list2);
    }




}
