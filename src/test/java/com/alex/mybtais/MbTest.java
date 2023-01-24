package com.alex.mybtais;

import com.alex.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-24-22:24
 */
public class MbTest {

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
}
