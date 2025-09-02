package com.zh8888.mapper;

import com.zh8888.pojo.Student;
import com.zh8888.pojo.StudentPageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> page(StudentPageParam param);

    void insert(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudentsById(List<Integer> ids);
}
