package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.expression.ScoreOutOfRangeException;
import com.zh8888.mapper.StudentMapper;
import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.entity.Student;
import com.zh8888.pojo.page.StudentPageParam;
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
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentPageParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        Page<Student> studentList = (Page<Student>) studentMapper.page(param);
        return new PageResult<>(studentList.getTotal(), studentList.getResult());
    }

    @Override
    public void addStudent(Student student) {
        log.info("添加学生信息：{}", student);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudentsById(List<Integer> ids) {
        studentMapper.deleteStudentsById(ids);
    }

    @Override
    public void addStudentViolation(Integer id, Short score) {
        Student student = studentMapper.getStudentById(id);
        if (score > 10) throw new ScoreOutOfRangeException("单次违纪分数不能大于10");
        if (student.getViolationScore() + score > 250) {
            throw new ScoreOutOfRangeException("违纪总分数不能大于250");
        }
        student.setViolationCount((short) (student.getViolationCount() + 1));
        student.setViolationScore((short) (student.getViolationScore() + score));
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }
}
