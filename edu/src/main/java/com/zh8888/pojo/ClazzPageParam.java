package com.zh8888.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzPageParam {
    Integer page= 1;
    Integer pageSize= 10;
    String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;

}
