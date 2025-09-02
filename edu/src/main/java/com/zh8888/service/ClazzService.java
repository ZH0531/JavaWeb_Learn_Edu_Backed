package com.zh8888.service;

import com.zh8888.pojo.Clazz;
import com.zh8888.pojo.ClazzPageParam;
import com.zh8888.pojo.PageResult;

import java.util.List;


public interface ClazzService {

    PageResult<Clazz> page(ClazzPageParam param);

    void addClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    void deleteClazzById(Integer id) ;

    List<Clazz> getClazzList();
}
