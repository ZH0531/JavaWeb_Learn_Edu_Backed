package com.zh8888.mapper;

import com.zh8888.pojo.Emp;
import com.zh8888.pojo.EmpExpr;
import com.zh8888.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> page(EmpQueryParam param);


    //    @Options(useGeneratedKeys = true, keyProperty = "id") 被xml覆盖
    void insert(Emp emp);
    void insertExpr(List<EmpExpr> exprList);

    void deleteEmpById(List<Integer> ids);
    void deleteExprById(List<Integer> ids);

    Emp getEmpById(Integer id);
    List<EmpExpr> getExprListByEmpId(Integer id);

    void updateEmp(Emp emp);
}
