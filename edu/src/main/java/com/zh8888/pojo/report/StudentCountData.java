package com.zh8888.pojo.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentCountData {
    private List<Object> clazzList;
    private List<Object> dataList;
}
