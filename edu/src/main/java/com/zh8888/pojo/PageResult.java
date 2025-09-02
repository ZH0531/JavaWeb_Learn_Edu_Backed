package com.zh8888.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<E> {
    long total;
    List<E> rows;
}
