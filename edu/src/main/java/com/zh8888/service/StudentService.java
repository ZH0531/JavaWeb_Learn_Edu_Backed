package com.zh8888.service;

import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.entity.Student;
import com.zh8888.pojo.page.StudentPageParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentPageParam param);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudentsById(List<Integer> ids);

    void addStudentViolation(Integer id, Short score);
}
