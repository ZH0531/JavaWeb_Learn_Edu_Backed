package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.mapper.StudentMapper;
import com.zh8888.pojo.PageResult;
import com.zh8888.pojo.Student;
import com.zh8888.pojo.StudentPageParam;
import com.zh8888.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper StudentMapper;

    @Override
    public PageResult<Student> page(StudentPageParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        Page<Student> studentList = (Page<Student>) StudentMapper.page(param);
        return new PageResult<>(studentList.getTotal(), studentList.getResult());
    }

    @Override
    public void addStudent(Student student) {
        log.info("添加学生信息：{}", student);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        StudentMapper.insert(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return StudentMapper.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        StudentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudentsById(List<Integer> ids) {
        StudentMapper.deleteStudentsById(ids);
    }
}
