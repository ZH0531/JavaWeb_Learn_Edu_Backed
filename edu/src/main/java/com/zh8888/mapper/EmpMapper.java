package com.zh8888.mapper;

import com.zh8888.pojo.entity.Emp;
import com.zh8888.pojo.entity.EmpExpr;
import com.zh8888.pojo.page.EmpPageParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    List<Emp> page(EmpPageParam param);


    //    @Options(useGeneratedKeys = true, keyProperty = "id") 被xml覆盖
    void insert(Emp emp);
    void insertExpr(List<EmpExpr> exprList);

    void deleteEmpById(List<Integer> ids);
    void deleteExprById(List<Integer> ids);

    Emp getEmpById(Integer id);
    List<EmpExpr> getExprListByEmpId(Integer id);

    void updateEmp(Emp emp);

    @MapKey("name")
    List<Map<String, Object>> getEmpGenderData();

    @MapKey("job")
    List<Map<String, Object>> getEmpJobData();

}
