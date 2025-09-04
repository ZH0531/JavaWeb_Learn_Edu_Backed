package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.pojo.page.EmpPageParam;
import com.zh8888.utils.AliyunOSSUtil;
import com.zh8888.mapper.EmpMapper;
import com.zh8888.pojo.entity.Emp;
import com.zh8888.pojo.entity.EmpExpr;
import com.zh8888.pojo.dto.PageResult;
import com.zh8888.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 分页查询员工信息
     * @param param  查询参数
     * @return 员工列表
     */
    @Override
    public PageResult<Emp> page(EmpPageParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());

        Page<Emp> empList = (Page<Emp>) empMapper.page(param);

        return new PageResult<>(empList.getTotal(), empList.getResult());
    }

    /**
     * 添加员工
     * @param emp  员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();

        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));//设置员工id
            empMapper.insertExpr(exprList);
        }
    }

    /**
     * 批量删除员工
     * @param ids  员工id列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(List<Integer> ids) {
        ids.forEach(id -> {
            try {
                log.info("删除员工图片路径：{}", getEmpById(id).getImage());
                aliyunOSSUtil.delete(getEmpById(id).getImage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        empMapper.deleteEmpById(ids);
        empMapper.deleteExprById(ids);
    }

    /**
     * 根据id查询员工信息
     * @param id  员工id
     * @return 员工信息
     */
    @Override
    public Emp getEmpById(Integer id) {
        log.info("查询员工{}", id);
        Emp emp = empMapper.getEmpById(id);
        log.info("查询员工{}结果{}", id, emp);

        emp.setExprList(empMapper.getExprListByEmpId(id));
        log.info("查询员工{}经历{}", id, emp.getExprList());
        return emp;
    }

    /**
     * 更新员工信息
     * @param emp  员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        log.info("更新员工{}", emp);
        empMapper.updateEmp(emp);
    }
    /**
     * 更新员工经历
     * @param emp  员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmpExpr(Emp emp) {
        log.info("删除员工经历{}",empMapper.getExprListByEmpId(emp.getId()));
        empMapper.deleteExprById(Collections.singletonList(emp.getId()));

        log.info("添加员工经历{}", emp.getExprList());
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));//设置员工id
            empMapper.insertExpr(exprList);
        }
    }

    @Override
    public List<Emp> getEmpList() {
        return empMapper.page(null);
    }
}
