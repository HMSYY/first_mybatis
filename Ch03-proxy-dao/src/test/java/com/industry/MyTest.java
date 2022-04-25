package com.industry;

import com.industry.dao.StudentDao;
import com.industry.domain.Student;
import com.industry.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void testSelectById(){
        //获取SqlSession对象
        SqlSession session = MyBatisUtil.getSqlSession();
        //获取dao代理
        StudentDao dao = session.getMapper(StudentDao.class);

        Student student = dao.selectById(1001);
        System.out.println("student = " + student);

        //关闭SqlSession对象
        session.close();
    }

    @Test
    public void testSelectStudents(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        List<Student> students = dao.selectStudents();
        students.forEach(student -> System.out.println("student = " + student));
        session.close();
    }

    @Test
    public void testSelectStudents2(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        //了解代理对象的内容：jdk.proxy2.$Proxy4 (=StudentDaoImpl)
        //生成真实的内存中的对象的类型的信息
        System.out.println("dao = " + dao.getClass().getName());
        List<Student> students = dao.selectStudents();
        students.forEach(student -> System.out.println("student = " + student));
        session.close();
    }
}
