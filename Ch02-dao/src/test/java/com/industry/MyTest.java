package com.industry;

import com.industry.domain.Student;
import com.industry.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void testSelectById(){
        //1. 获取SqlSession对象
        SqlSession session = MyBatisUtil.getSqlSession();
        //2. 指定SqlId
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectById";
        //3. 执行SqlSession的方法，表示执行sql语句
        Student student = session.selectOne(sqlId,1001);
        System.out.println("查询的结果 = " + student);
        //4. 关闭SqlSession对象
        session.close();
    }

    @Test
    public void testSelectStudents(){
        //1. 获取SqlSession对象
        SqlSession session = MyBatisUtil.getSqlSession();
        //2. 指定SqlId
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectStudents";
        //3. 执行SqlSession的方法，表示执行sql语句
        List<Student> students = session.selectList(sqlId);

        //遍历得到输出结果
        for(Student stu : students){
            System.out.println("student = " + stu);
        }

        //4. 关闭SqlSession对象
        session.close();
    }

    @Test
    public void insertStudent(){
        //1. 获取SqlSession对象
        SqlSession session = MyBatisUtil.getSqlSession();
        //2. 指定SqlId
        String sqlId = "com.industry.dao.StudentDao" + "." + "insertStudent";

        Student student = new Student();
        student.setId(1007);
        student.setName("东东");
        student.setEmail("dongdong@qq.com");
        student.setAge(30);

        //3. 执行SqlSession的方法，表示执行sql语句
        int rows = session.insert(sqlId,student);
        //4. 事务提交
        session.commit();

        System.out.println("insert的行数 = " + rows);

        //5. 关闭SqlSession对象
        session.close();
    }
}
