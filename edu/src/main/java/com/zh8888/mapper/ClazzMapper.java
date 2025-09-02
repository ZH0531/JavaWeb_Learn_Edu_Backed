package com.zh8888.mapper;

import com.zh8888.pojo.Clazz;
import com.zh8888.pojo.ClazzPageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> page(ClazzPageParam param);

    void insert(Clazz clazz);

    Clazz getClazzById(Integer id);

}
