package com.zh8888.service.Impl;

import com.zh8888.mapper.EmpMapper;
import com.zh8888.mapper.StudentMapper;
import com.zh8888.pojo.report.JobReportData;
import com.zh8888.pojo.report.StudentCountData;
import com.zh8888.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;


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

    @Override
    public StudentCountData getStudentCountData() {
        List<Map<String, Object>> studentCountData = studentMapper.getStudentCountData();
        List<Object> degree = studentCountData.stream().map(Map -> Map.get("clazzName")).toList();
        List<Object> num = studentCountData.stream().map(Map -> Map.get("value")).toList();
        return new StudentCountData(degree, num);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }
}
