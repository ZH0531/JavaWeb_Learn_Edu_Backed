package com.zh8888.controller;

import com.zh8888.pojo.Emp;
import com.zh8888.pojo.EmpQueryParam;
import com.zh8888.pojo.Result;
import com.zh8888.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam param) {
        log.info("查询员工列表");
        log.info("参数：{}", param);
        return Result.success(empService.page(param));
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp) {
        log.info("添加员工");
        log.info("参数：{}", emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteById(@RequestParam List<Integer> ids) {
        log.info("批量删除员工");
        log.info("参数：{}", ids);
        empService.deleteById(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {
        log.info("查询员工byID");
        log.info("参数：{}", id);
        return Result.success(empService.getEmpById(id));
    }

    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("更新员工");
        log.info("参数：{}", emp);
        empService.updateEmp(emp);
        log.info("更新员工基本信息成功");
        empService.updateEmpExpr(emp);
        log.info("更新工作经历成功");
        return Result.success();
    }

}
