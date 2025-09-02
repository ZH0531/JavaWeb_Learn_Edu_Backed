package com.zh8888.controller;

import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.dto.Result;
import com.zh8888.pojo.entity.Student;
import com.zh8888.pojo.page.StudentPageParam;
import com.zh8888.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService StudentService;
    @GetMapping
    public Result page(StudentPageParam param) {
        log.info("分页查询参数：：{}", param);
        PageResult<Student> studentList = StudentService.page(param);
        return Result.success(studentList);
    }

    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        log.info("添加学生信息：{}", student);
        StudentService.addStudent(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("根据ID查询学生信息：{}", id);
        Student student = StudentService.getStudentById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result updateStudent(@RequestBody Student student) {
        log.info("更新学生信息：{}", student);
        StudentService.updateStudent(student);
        return Result.success();
    }

    @DeleteMapping("/ids")
    public Result deleteById(@RequestParam List<Integer> ids) {
        log.info("批量删除学生信息：{}", ids);
        StudentService.deleteStudentsById(ids);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result addViolation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("添加学生违规信息：{}", id);
        StudentService.addStudentViolation(id, score);
        return Result.success();
    }
}
