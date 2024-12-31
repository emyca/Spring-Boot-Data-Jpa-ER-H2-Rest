package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CourseDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course dtoCreateToEntity(CourseDtoRequest request) {
        Course course = new Course();
        course.setId(request.id());
        course.setName(request.name());
        return course;
    }

    public Course dtoUpdateByIdToEntity(CourseDtoRequest request,
                                        Course courseToUpdate) {
        courseToUpdate.setName(request.name());
        return courseToUpdate;
    }
}
