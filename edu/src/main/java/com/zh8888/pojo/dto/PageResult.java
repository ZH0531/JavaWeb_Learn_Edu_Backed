package com.zh8888.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页结果
 */
@Data
@AllArgsConstructor
public class PageResult<E> {
    long total;
    List<E> rows;
}
