package com.zh8888.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
