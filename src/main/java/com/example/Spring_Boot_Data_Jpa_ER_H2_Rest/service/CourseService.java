package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CourseDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(CourseDtoRequest request);
    Course setToByStudentId(Long id, CourseDtoRequest request);
    List<Course> getAll();
    Course getById(Long id);
    List<Course> getAllCoursesByStudentId(Long studentId);
    Course updateById(Long id, CourseDtoRequest request);
    void unsetByIdFromStudentId(Long studentId, Long courseId);
    void deleteById(Long id);
}
