package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StudentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;

import java.util.List;

public interface StudentService {
    Student create(StudentDtoRequest request);
    List<Student> getAll();
    Student getById(Long id);
    List<Student> getAllStudentsByCourseId(Long courseId);
    Student updateById(Long id, StudentDtoRequest request);
    void deleteById(Long id);
}
