package com.zh8888.controller;

import com.zh8888.pojo.*;
import com.zh8888.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class clazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzPageParam param) {
        log.info("查询班级信息：{}", param);
        PageResult<Clazz> clazzList = clazzService.page(param);
        return Result.success(clazzList);
    }

    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("添加班级");
        log.info("参数：{}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("查询班级byID");
        log.info("参数：{}", id);
        return Result.success(clazzService.getClazzById(id));
    }


}
