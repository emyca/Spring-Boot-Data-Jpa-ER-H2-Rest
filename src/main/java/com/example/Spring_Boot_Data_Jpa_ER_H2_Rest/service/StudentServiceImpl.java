package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StudentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.StudentMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.CourseRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student create(StudentDtoRequest request) {
        return studentRepository.save(
                studentMapper.dtoCreateToEntity(request));
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(Long id)
            throws ResourceException {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Student with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public List<Student> getAllStudentsByCourseId(Long courseId)
            throws ResourceException {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceException(
                    "Course with id %s has not been found!"
                            .formatted(courseId));
        }
        return studentRepository.findStudentsByCoursesId(courseId);
    }

    @Override
    public Student updateById(Long id, StudentDtoRequest request)
            throws ResourceException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Student with id %s has not been found!"
                                        .formatted(id)));
        return studentRepository.save(studentMapper
                .dtoUpdateByIdToEntity(request, student));
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException {
        studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Student with id %s has not been found!"
                                        .formatted(id)));
        studentRepository.deleteById(id);
    }
}
