package com.zh8888.service;

import com.zh8888.pojo.dto.PageResult;
import com.zh8888.pojo.entity.OperateLog;

public interface LogService {
    PageResult<OperateLog> page(int page, int pageSize);
}
