package com.zh8888.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobReportData {
    private List<Object> jobList;
    private List<Object> dataList;
}
