package com.zh8888.pojo.page;

import lombok.Data;

@Data
public class StudentPageParam {
    Integer page= 1;
    Integer pageSize= 10;
    String name;
    Integer degree;
    Integer clazzId;


}
