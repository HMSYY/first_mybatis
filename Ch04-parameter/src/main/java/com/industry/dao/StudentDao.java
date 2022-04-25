package com.industry.dao;

import com.industry.domain.Student;
import com.industry.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    Student selectById(Integer id);

    Student selectByEmail(String email);

    /*
    多个简单类型的参数
     */
    List<Student> selectByNameOrAge(@Param(value = "myName") String name,
                                    @Param(value = "myAge") Integer age);

    /*
    一个java对象作为参数(对象有属性，每个属性有set，get方法)
     */
    List<Student> selectByObject(Student student);

    List<Student> selectByQueryParam(QueryParam param);

    /*
      使用位置获取参数
     */
    List<Student> selectByPosition(String name,Integer age);

    /*
      使用Map作为参数
     */
    List<Student> selectStudentByMap(Map<String,Object> map);
}
















