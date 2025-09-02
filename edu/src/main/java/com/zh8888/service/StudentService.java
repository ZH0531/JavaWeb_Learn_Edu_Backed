package com.zh8888.service;

import com.zh8888.pojo.PageResult;
import com.zh8888.pojo.Student;
import com.zh8888.pojo.StudentPageParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentPageParam param);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudentsById(List<Integer> ids);
}
