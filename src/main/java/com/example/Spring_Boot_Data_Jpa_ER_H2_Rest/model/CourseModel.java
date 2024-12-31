package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class CourseModel {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Student> students;

    public static CourseModel getModel(Course course) {
        CourseModel model = new CourseModel();
        model.setId(course.getId());
        model.setName(course.getName());
        List<Student> studentList = new ArrayList<>();
        model.setStudents(studentList);
        Set<Student> studentSet = course.getStudents();
        if (studentSet != null) {
            for (Student student : studentSet) {
                Student _student = new Student();
                _student.setId(student.getId());
                _student.setFirstName(student.getFirstName());
                _student.setLastName(student.getLastName());
                _student.setEmail(student.getEmail());
                model.getStudents().add(_student);
            }
            model.setStudents(model.getStudents());
        }
        return model;
    }

    public static List<CourseModel> getModel(List<Course> courseList) {
        List<CourseModel> modelList = new ArrayList<>();
        if (courseList != null) {
            for (Course course : courseList) {
                CourseModel model = new CourseModel();
                model.setId(course.getId());
                model.setName(course.getName());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
