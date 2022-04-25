package com.industry;

import com.industry.dao.StudentDao;
import com.industry.dao.impl.StudentDaoImpl;
import com.industry.domain.Student;
import org.junit.Test;

import java.util.List;

public class MyTest2 {

    /**
     * 执行sql语句的必要的信息：
     * String sqlId = "com.industry.dao.StudentDao" + "." + "selectById";
     * Student student = sqlSession.selectOne(sqlId, id);
     *
     * 测试方法中：调用dao的方法
     * Student student = dao.selectById(1001);
     *
     * 1）dao：通过反射机制能得到 全限定类型名称
     *   dao是StudentDao类型的，全限定名称是com.industry.dao.StudentDao
     * 2）selectById：dao中的方法名称，方法名称就是mapper文件中标签的id
     *    通过dao.selectById()能得到 sqlId = "com.industry.dao.StudentDao.selectById";
     * 3）确定调用sqlSession的哪个方法
     *    1.根据dao接口的返回值
     *      如果返回的是一个对象，例如student，调用sqlSession.selectOne();
     *      如果返回的是list，调用sqlSession.selectList();
     *    2.根据mapper文件中的标签
     *      如果标签是insert，调用sqlSession.insert();
     *      如果标签是select，调用sqlSession.select..();
     *
     * mybatis框架，发现使用dao的方法调用，可以确定执行sql语句的必要的信息，所以mybatis可以简化dao对象的实现。
     * 由mybatis框架在执行期间，根据你的dao接口，创建一个内存中的接口的实现类对象。
     * mybatis把这个技术称作dao代理技术（动态代理。dao的动态代理）。
     *
     * dao代理技术：由mybatis创建 StudentDao接口的实现类 Proxy(即StudentDaoImpl)，
     *            使用框架创建的StudentDaoImpl代替你自己手工实现的StudentDaoImpl类的功能，
     *            不用开发人员自己写dao接口的实现类。
     *    即，创建一个对象，来代替dao实现类的功能。
     */
    @Test
    public void testSelectOne(){

        //要使用dao的方法
        //接口类型     变量 = new 接口的实现类();
        StudentDao dao = new StudentDaoImpl();
        Student student = dao.selectById(1001);
        System.out.println("通过dao执行方法，得到的对象 = " + student);
    }

    @Test
    public void testSelectStudents(){

        StudentDao dao = new StudentDaoImpl();
        List<Student> students = dao.selectStudents();

        for(Student stu : students){
            System.out.println("student = " + stu);
        }
    }

    @Test
    public void testInsertStudent(){

        StudentDao dao = new StudentDaoImpl();

        Student student = new Student();
        student.setId(1008);
        student.setName("宝宝");
        student.setEmail("baobao@qq.com");
        student.setAge(24);

        int rows = dao.insertStudent(student);
        System.out.println("insert的行数 = " + rows);
    }
}
























