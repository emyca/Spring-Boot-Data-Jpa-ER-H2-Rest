package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StudentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student dtoCreateToEntity(StudentDtoRequest request) {
        Student student = new Student();
        student.setId(request.id());
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        return student;
    }

    public Student dtoUpdateByIdToEntity(StudentDtoRequest request,
                                         Student studentToUpdate) {
        studentToUpdate.setFirstName(request.firstName());
        studentToUpdate.setLastName(request.lastName());
        studentToUpdate.setEmail(request.email());
        return studentToUpdate;
    }
}
