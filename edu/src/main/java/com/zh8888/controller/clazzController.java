package com.zh8888.controller;

import com.zh8888.pojo.Clazz;
import com.zh8888.pojo.ClazzPageParam;
import com.zh8888.pojo.PageResult;
import com.zh8888.pojo.Result;
import com.zh8888.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
