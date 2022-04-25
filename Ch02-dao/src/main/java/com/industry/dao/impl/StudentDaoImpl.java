package com.industry.dao.impl;

import com.industry.dao.StudentDao;
import com.industry.domain.Student;
import com.industry.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student selectById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectById";
        Student student = sqlSession.selectOne(sqlId, id);
        sqlSession.close();
        return student;
    }

    @Override
    public List<Student> selectStudents() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String sqlId = "com.industry.dao.StudentDao" + "." + "selectStudents";
        List<Student> students = sqlSession.selectList(sqlId);
        sqlSession.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String sqlId = "com.industry.dao.StudentDao" + "." + "insertStudent";
        int rows = sqlSession.insert(sqlId, student);
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }
}
