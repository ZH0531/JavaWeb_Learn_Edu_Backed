package com.zh8888.controller;

import com.zh8888.pojo.JobReportData;
import com.zh8888.pojo.Result;
import com.zh8888.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empGenderData")
    public Result getEmpGenderReport() {
        log.info("查询员工性别数据");
        List<Map<String, Object>> empGenderData = reportService.getEmpGenderData();
        return Result.success(empGenderData);
    }
    @GetMapping("/empJobData")
    public Result getEmpJobReport() {
        log.info("查询员工职位数据");
        JobReportData jobReportData = reportService.getEmpJobData();
        return Result.success(jobReportData);
    }
}
