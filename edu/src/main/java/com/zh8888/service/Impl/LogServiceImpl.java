package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.mapper.EmpMapper;
import com.zh8888.mapper.OperateLogMapper;
import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.entity.Emp;
import com.zh8888.pojo.entity.OperateLog;
import com.zh8888.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    OperateLogMapper operateLogMapper;

    @Autowired
    EmpMapper empMapper;

    @Override
    public PageResult<OperateLog> page(int page, int pageSize) {
        //分页插件
        PageHelper.startPage(page, pageSize);

        //根据数据库日志员工ID获取员工姓名返回给前端
        Page<OperateLog> operateLogList = (Page<OperateLog>) operateLogMapper.page();
        operateLogList.getResult().forEach(operateLog -> {
            Emp emp = empMapper.getEmpById(operateLog.getOperateEmpId());
            String name = emp.getName();
            operateLog.setOperateEmpName(name);
        });
        return new PageResult<>(operateLogList.getTotal(), operateLogList.getResult());
    }
}
