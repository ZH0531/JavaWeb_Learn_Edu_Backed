package com.zh8888.service;

import com.zh8888.pojo.report.JobReportData;
import com.zh8888.pojo.report.StudentCountData;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Map<String,Object>> getEmpGenderData();

    JobReportData getEmpJobData();

    StudentCountData getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
