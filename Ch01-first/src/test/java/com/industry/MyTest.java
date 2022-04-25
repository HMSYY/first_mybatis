package com.industry;

import com.industry.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyTest {

    //测试mybatis执行sql语句
    @Test
    public void testSelectStudentById() throws IOException {

        //调用mybatis某个对象的方法，执行mapper文件中的sql语句
        //mybatis核心类：SqlSessionFactory

        //1.定义mybatis主配置文件的位置，从类路径开始的相对路径
        String config = "mybatis.xml";
        //2.读取主配置文件，使用mybatis框架中的Resources类
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactory对象，使用SqlSessionFactoryBuilder类
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //4.获取SqlSession对象(new不出来，其是一个接口)
        SqlSession session = factory.openSession();

        //5.指定要执行的sql语句的标识(id)
        //sql语句的id = namespace + "." + "select|update|delete|insert标签的id属性值"
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectStudentById";
        //也可以这种形式：
        //String sqlId = "com.industry.dao.StudentDao.selectStudentById";

        //6.通过SqlSession的方法，执行sql语句
        Student student = session.selectOne(sqlId);
        System.out.println("使用mybatis查询一个学生：" + student);

        //7.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testSelectStudentById2() throws IOException {

        //调用mybatis某个对象的方法，执行mapper文件中的sql语句
        //mybatis核心类：SqlSessionFactory

        //1.定义mybatis主配置文件的位置，从类路径开始的相对路径
        String config = "mybatis.xml";
        //2.读取主配置文件，使用mybatis框架中的Resources类
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactory对象，使用SqlSessionFactoryBuilder类
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //4.获取SqlSession对象(new不出来，其是一个接口)
        SqlSession session = factory.openSession();

        //5.指定要执行的sql语句的标识(id)
        //sql语句的id = namespace + "." + "select|update|delete|insert标签的id属性值"
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectStudentById";
        //也可以这种形式：
        //String sqlId = "com.industry.dao.StudentDao.selectStudentById";

        //6.通过SqlSession的方法，执行sql语句
        //StudentDao.xml中的#{studentId}可以变成对象类型的指定要查询的值
        Student student = session.selectOne(sqlId,1002);
        System.out.println("使用mybatis查询一个学生：" + student);

        //7.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testInsertStudent1() throws IOException {

        //调用mybatis某个对象的方法，执行mapper文件中的sql语句
        //mybatis核心类：SqlSessionFactory

        //1.定义mybatis主配置文件的位置，从类路径开始的相对路径
        String config = "mybatis.xml";
        //2.读取主配置文件，使用mybatis框架中的Resources类
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactory对象，使用SqlSessionFactoryBuilder类
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //4.获取SqlSession对象(new不出来，其是一个接口)
        SqlSession session = factory.openSession();

        //5.指定要执行的sql语句的标识(id)
        //sql语句的id = namespace + "." + "select|update|delete|insert标签的id属性值"
        String sqlId = "com.industry.dao.StudentDao" + "." + "insertStudent";
        

        //6.通过SqlSession的方法，执行sql语句
        int rows = session.insert(sqlId);
        System.out.println("使用mybatis查询一个学生：rows = " + rows);

        //mybatis默认执行sql语句是手工提交事务模式（可改变），在做Insert,update,delete后需要提
        //交事务
        session.commit();

        //7.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testInsertStudent2() throws IOException {

        //调用mybatis某个对象的方法，执行mapper文件中的sql语句
        //mybatis核心类：SqlSessionFactory

        //1.定义mybatis主配置文件的位置，从类路径开始的相对路径
        String config = "mybatis.xml";
        //2.读取主配置文件，使用mybatis框架中的Resources类
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建SqlSessionFactory对象，使用SqlSessionFactoryBuilder类
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //4.获取SqlSession对象(new不出来，其是一个接口)
        SqlSession session = factory.openSession();

        //5.指定要执行的sql语句的标识(id)
        //sql语句的id = namespace + "." + "select|update|delete|insert标签的id属性值"
        String sqlId = "com.industry.dao.StudentDao" + "." + "insertStudent";


        //6.通过SqlSession的方法，执行sql语句
        Student student = new Student();
        student.setId(1005);
        student.setName("张飞");
        student.setEmail("zhangfei@qq.com");
        student.setAge(24);

        int rows = session.insert(sqlId,student);
        System.out.println("使用mybatis查询一个学生：rows = " + rows);

        //mybatis默认执行sql语句是手工提交事务模式（可改变），在做Insert,update,delete后需要提
        //交事务
        session.commit();

        //7.关闭SqlSession对象
        session.close();
    }

    @Test
    public void testAutoCommitInsertStudent1() throws IOException {

        String config = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建自动提交事务的SqlSession对象，用参数true
        //因此最后可以不添加"session.commit();"手动提交事务了
        SqlSession session = factory.openSession(true);

        String sqlId = "com.industry.dao.StudentDao" + "." + "insertStudent";

        Student student = new Student();
        student.setId(1006);
        student.setName("小乔");
        student.setEmail("qiao@qq.com");
        student.setAge(23);

        int rows = session.insert(sqlId,student);
        System.out.println("使用mybatis查询一个学生：rows = " + rows);

        session.close();
    }
}












