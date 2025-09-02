package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.mapper.ClazzMapper;
import com.zh8888.pojo.Clazz;
import com.zh8888.pojo.ClazzPageParam;
import com.zh8888.pojo.PageResult;
import com.zh8888.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzPageParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());

        Page<Clazz> clazzList = (Page<Clazz>) clazzMapper.page(param);
        clazzList.forEach(clazz -> {
           if (clazz.getEndDate().isBefore(LocalDate.now()))
               clazz.setStatus("已结课");
           else if (clazz.getBeginDate().isAfter(LocalDate.now()))
               clazz.setStatus("未开班");
           else
               clazz.setStatus("在读中");

        });

        return new PageResult<>(clazzList.getTotal(), clazzList.getResult());
    }
}
