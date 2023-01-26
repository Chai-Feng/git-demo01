package com.alex.mybtais.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title:
 * @Description: TODO
 * @author: Alex
 * @Version:
 * @date 2023-01-26-15:17
 */
public class SqlSessionUtil {

    public static  <T> T getMapper(Class<T> type ){
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
}
