package com.zh8888.service;

import com.zh8888.pojo.Emp;
import com.zh8888.pojo.EmpQueryParam;
import com.zh8888.pojo.PageResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam param);

    void addEmp(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getEmpById(Integer id);

    void updateEmp(Emp emp);

    void updateEmpExpr(Emp emp);
}
