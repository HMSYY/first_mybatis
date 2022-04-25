package com.industry.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 工具类：创建SqlSession对象
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory = null;

    //static块
    static{

        String config = "mybatis.xml";
        //捕获主配置文件
        try {
            InputStream inputStream =  Resources.getResourceAsStream(config);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建方法，调这个方法，就能获取SqlSession对象
    public static SqlSession getSqlSession(){
        SqlSession session = null;
        if(factory != null){
            //若代码要对事务有处理，则应该用没有参数的方式
            session = factory.openSession();//也可以选择openSession(true);即创建对象之后，事务自动提交
        }
        return session;
    }
}
