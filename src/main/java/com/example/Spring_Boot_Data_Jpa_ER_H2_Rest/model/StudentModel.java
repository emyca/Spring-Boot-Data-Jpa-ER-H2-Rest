package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class StudentModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Course> courses;

    public static StudentModel getModel(Student student) {
        StudentModel model = new StudentModel();
        model.setId(student.getId());
        model.setFirstName(student.getFirstName());
        model.setLastName(student.getLastName());
        model.setEmail(student.getEmail());
        List<Course> courseList = new ArrayList<>();
        model.setCourses(courseList);
        Set<Course> courseSet = student.getCourses();
        if (courseSet != null) {
            for (Course course : courseSet) {
                Course _course = new Course();
                _course.setId(course.getId());
                _course.setName(course.getName());
                model.getCourses().add(_course);
            }
        }
        return model;
    }

    public static List<StudentModel> getModel(List<Student> studentList) {
        List<StudentModel> modelList = new ArrayList<>();
        if (studentList != null) {
            for (Student student : studentList) {
                StudentModel model = new StudentModel();
                model.setId(student.getId());
                model.setFirstName(student.getFirstName());
                model.setLastName(student.getLastName());
                model.setEmail(student.getEmail());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
