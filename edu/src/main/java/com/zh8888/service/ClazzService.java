package com.zh8888.service;

import com.zh8888.pojo.Clazz;
import com.zh8888.pojo.ClazzPageParam;
import com.zh8888.pojo.PageResult;


public interface ClazzService {
    PageResult<Clazz> page(ClazzPageParam param);

}
