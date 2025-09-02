package com.zh8888.service;

import com.zh8888.pojo.JobReportData;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Map<String,Object>> getEmpGenderData();

    JobReportData getEmpJobData();

}
