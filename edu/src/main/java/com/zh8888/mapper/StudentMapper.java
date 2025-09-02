package com.zh8888.mapper;

import com.zh8888.pojo.entity.Student;
import com.zh8888.pojo.page.StudentPageParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> page(StudentPageParam param);

    void insert(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudentsById(List<Integer> ids);

    @MapKey("clazz")
    List<Map<String, Object>> getStudentCountData();

    @MapKey("degree")
    List<Map<String, Object>> getStudentDegreeData();
}
