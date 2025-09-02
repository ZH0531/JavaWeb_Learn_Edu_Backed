package com.zh8888.service;

import com.zh8888.pojo.entity.Clazz;
import com.zh8888.pojo.page.ClazzPageParam;
import com.zh8888.pojo.dto.PageResult;

import java.util.List;


public interface ClazzService {

    PageResult<Clazz> page(ClazzPageParam param);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    void deleteClazzById(Integer id) ;

    List<Clazz> getClazzList();
}
