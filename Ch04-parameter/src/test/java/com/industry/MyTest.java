package com.industry;

import com.industry.dao.StudentDao;
import com.industry.domain.Student;
import com.industry.utils.MyBatisUtil;
import com.industry.vo.QueryParam;
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
    public void testOneParameter(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student = dao.selectByEmail("dongdong@qq.com");
        System.out.println("student = " +student);
        session.close();
    }

    @Test
    public void testSelectByNameOrAge(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        List<Student> students = dao.selectByNameOrAge("李四",20);

        students.forEach(student -> System.out.println("student = " + student));

        session.close();
    }

    @Test
    public void testSelectByObject(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李思思");
        student.setAge(22);

        List<Student> students = dao.selectByObject(student);

        students.forEach(stu -> System.out.println("student = " + stu));

        session.close();
    }

    @Test
    public void testSelectByObject2(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        QueryParam param = new QueryParam();
        param.setP1("东东");
        param.setP2(20);

        List<Student> students = dao.selectByQueryParam(param);

        students.forEach(stu -> System.out.println("student = " + stu));

        session.close();
    }

    @Test
    public void testSelectByPosition(){
        SqlSession session = MyBatisUtil.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        List<Student> students = dao.selectByPosition("李四",20);

        students.forEach(stu -> System.out.println("student = " + stu));
        session.close();
    }
}
