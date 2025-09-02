package com.zh8888.controller;

import com.zh8888.pojo.Dept;
import com.zh8888.pojo.Result;
import com.zh8888.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result queryAll() {
        List<Dept> deptList = deptService.findAll();
        log.info("查询所有部门信息：{}", deptList);
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result deleteById(Integer id) {
        deptService.deleteById(id);
        log.info("删除部门信息：id={}", id);
        return Result.success();
    }

    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        log.info("新增部门信息：{}", dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        Dept dept = deptService.getDeptById(id);
        log.info("根据ID查询部门信息：{}", dept);
        return Result.success(dept);
    }

    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        log.info("修改部门信息：{}", dept);
        return Result.success();
    }
}
