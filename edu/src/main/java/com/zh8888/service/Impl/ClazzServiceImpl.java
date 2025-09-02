package com.zh8888.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zh8888.expression.ClazzNotEmptyException;
import com.zh8888.mapper.ClazzMapper;
import com.zh8888.pojo.entity.Clazz;
import com.zh8888.pojo.page.ClazzPageParam;
import com.zh8888.pojo.dto.PageResult;
import com.zh8888.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        log.info("查询班级{}", id);
        Clazz clazz = clazzMapper.getClazzById(id);
        log.info("查询班级{}结果{}", id, clazz);
        return clazz;
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        log.info("更新员工{}", clazz);
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public void deleteClazzById(Integer id)  {
        Integer count = clazzMapper.getStudentCountById(id);//获取班级下人数
        if (count > 0) throw new ClazzNotEmptyException("班级下有学生，不能删除!");
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.page(null);
    }
}

