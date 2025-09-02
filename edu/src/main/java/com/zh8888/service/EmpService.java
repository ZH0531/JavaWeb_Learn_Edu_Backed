package com.zh8888.service;

import com.zh8888.pojo.entity.Emp;
import com.zh8888.pojo.page.EmpPageParam;
import com.zh8888.pojo.dto.PageResult;

import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpPageParam param);

    void addEmp(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    void updateEmpExpr(Emp emp);

    List<Emp> getEmpList();
}
