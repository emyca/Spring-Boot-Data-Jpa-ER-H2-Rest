package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CourseDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.CourseMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.CourseRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Course create(CourseDtoRequest request) {
        String name = request.name();
        if (courseRepository.findCourseByName(name) != null)
            throw new ResourceException(
                    "Course with name '%s' already exists!"
                            .formatted(name));
        else return courseRepository.save(
                courseMapper.dtoCreateToEntity(request));
    }

    @Override
    public Course setToByStudentId(Long id, CourseDtoRequest request)
            throws ResourceException {
        return studentRepository.findById(id).map(student -> {
            long courseId = request.id();
            // Course існує
            if (courseId != 0L) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() ->
                                new ResourceException(
                                        "Course with id %s has not been found!"
                                                .formatted(courseId)));
                student.addCourse(course);
                studentRepository.save(student);
                return course;
            }
            // Додавання та створення нового Course
            Course _course =
                    courseMapper.dtoCreateToEntity(request);
            student.addCourse(_course);
            return courseRepository.save(_course);
        }).orElseThrow(() ->
                new ResourceException(
                        "Student with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Course with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public List<Course> getAllCoursesByStudentId(Long studentId)
            throws ResourceException {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceException(
                    "Student with id %s has not been found!"
                            .formatted(studentId));
        }
        return courseRepository.findCoursesByStudentsId(studentId);
    }

    @Override
    public Course updateById(Long id, CourseDtoRequest request)
            throws ResourceException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Course with id %s has not been found!"
                                        .formatted(id)));
        return courseRepository.save(courseMapper
                .dtoUpdateByIdToEntity(request, course));
    }

    @Override
    public void unsetByIdFromStudentId(Long studentId, Long courseId)
            throws ResourceException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceException(
                                "Student with id %s has not been found!"
                                        .formatted(studentId)));
        student.removeCourse(courseId);
        studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException,
            DataIntegrityViolationException {
        courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Course with id %s has not been found!"
                                        .formatted(id)));
        // Can throw DataIntegrityViolationException
        courseRepository.deleteById(id);
    }
}
