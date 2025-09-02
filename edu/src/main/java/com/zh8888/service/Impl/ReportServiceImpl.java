package com.zh8888.service.Impl;

import com.zh8888.mapper.EmpMapper;
import com.zh8888.pojo.JobReportData;
import com.zh8888.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.getEmpGenderData();
    }

    @Override
    public JobReportData getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();
        List<Object> job = empJobData.stream().map(Map -> Map.get("job")).toList();
        List<Object> num = empJobData.stream().map(Map -> Map.get("data")).toList();
        return new JobReportData(job, num);
    }
}
