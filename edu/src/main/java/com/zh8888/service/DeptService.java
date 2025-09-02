package com.zh8888.service;

import com.zh8888.pojo.entity.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void deleteById(Integer id);

    void addDept(Dept dept);

    Dept getDeptById(Integer id);

    void updateDept(Dept dept);
}
