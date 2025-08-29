package com.zh8888.controller;

import com.zh8888.pojo.Dept;
import com.zh8888.pojo.Result;
import com.zh8888.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @GetMapping("/depts")
    public Result queryAll() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);

    }

    @DeleteMapping("/depts")
    public Result deleteById(Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result addDept(@RequestBody Dept dept) {
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        deptService.updateDept(dept);
        return Result.success();
    }


}
