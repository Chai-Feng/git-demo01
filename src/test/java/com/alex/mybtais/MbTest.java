package com.alex.mybtais;

import com.alex.mybatis.entity.User;
import com.alex.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-24-22:24
 */
public class MbTest {


    public UserMapper getMapper(){
        InputStream is = null;
        UserMapper mapper =null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession = factory.openSession(true);
             mapper = sqlSession.getMapper(UserMapper.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mapper;

    }
    @Test
    public  void test1(){
        InputStream is=null;
        try {
            //读取MyBatis的核心配置文件
             is = Resources.getResourceAsStream("mybatis-config.xml"); //mybatis-config.xml
            //创建SqlSessionFactoryBuilder对象
            //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
            //SqlSession sqlSession = sqlSessionFactory.openSession();
            //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
            SqlSession sqlSession = sessionFactory.openSession(true);
            //通过代理模式创建UserMapper接口的代理实现类对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //调用UserMapper接口中的方法，就可以根据UserMapper的全类名匹配元素文件，通过调用的方法名匹配
           // 映射文件中的SQL标签，并执行标签中的SQL语句
            int result = mapper.insertUser();
            //sqlSession.commit();

            System.out.println("数据库中 "+result+" 行 改动");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            //不用执行 sqlsession 关闭，
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testSelect(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession = factory.openSession(true);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            User userById = mapper.getUserById(1);

            System.out.println(userById);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testSelectList(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession = factory.openSession(true);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            List<User> userList = mapper.getUserList();

            System.out.println(userList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void testGetCount(){
        UserMapper mapper = getMapper();
        int count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void testToMap(){
        UserMapper mapper = getMapper();
        Map<String, Object> UsertoMap = mapper.getUserToMap(1);
        //注意：输出的结果，并不是按照User的属性 顺序的， 这体现了Map使用Hash 的无序性，根据Hash值存入元素
        System.out.println(UsertoMap);
    }


    @Test
    public void testGetAllUserToMap(){
        UserMapper mapper = getMapper();
        List<Map<String, Object>> allUserToMap = mapper.getAllUserToMap();
        System.out.println(allUserToMap);
    }

    @Test
    public void testGetAllUserToMap2(){
        UserMapper mapper = getMapper();
        Map allUserToMap = mapper.getAllUserToMap2();
        System.out.println(allUserToMap);
    }

    @Test
    public void testMohu(){
        UserMapper mapper = getMapper();
        List<User> users = mapper.getMohu("gmail");
        System.out.println(users);
    }

    @Test
    public void testDeleteMore(){
        UserMapper mapper = getMapper();
        //注意这种书写格式 id之间用逗号隔开
        int i = mapper.deleteMore("3,4");
        System.out.println(i);
    }

    @Test
    public void testInsert(){
        UserMapper mapper = getMapper();
        User user = new User();
        user.setUserName("Alex");
        user.setAge(23);
        user.setGender("男");
        user.setPassWord("103377");
       // user.setEmail(null);
        int i = mapper.insertUserGeneratedKeys(user);
        //注意，当插入成功后，自增主键的值会存回User 的id属性中
        System.out.println("添加 "+i+ " 行记录 \n"+"新增记录 id= "+ user.getId());
    }

}
