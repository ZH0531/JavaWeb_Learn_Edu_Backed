package com.zh8888.controller;

import com.zh8888.mapper.OperateLogMapper;
import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.dto.Result;
import com.zh8888.pojo.entity.OperateLog;
import com.zh8888.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<OperateLog> pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
