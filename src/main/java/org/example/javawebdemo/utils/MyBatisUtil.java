package org.example.javawebdemo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisUtil {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }
}
