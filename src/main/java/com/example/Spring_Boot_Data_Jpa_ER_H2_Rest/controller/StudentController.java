package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StudentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StudentDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Student;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.StudentModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService service;

    @PostMapping("/students")
    public ResponseEntity<StudentDtoResponse> createStudent(
            @RequestBody StudentDtoRequest request) {
        Student student = service.create(request);
        return (student != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new StudentDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(StudentDtoResponse
                                        .Message.SUCCESS_SAVE_MSG.getMessage())
                                .student(StudentModel.getModel(student))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new StudentDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(StudentDtoResponse
                                        .Message.FAILURE_SAVE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/students")
    public ResponseEntity<StudentDtoResponse> getAllStudents() {
        List<Student> list = service.getAll();
        if (!list.isEmpty()) {
            List<StudentModel> _list = new ArrayList<>();
            for (Student student : list)
                _list.add(StudentModel.getModel(student));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StudentDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .students(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(StudentDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .students(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StudentDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .student(StudentModel.getModel(service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/courses/{id}/students")
    public ResponseEntity<StudentDtoResponse> getAllStudentsByCourseId(
            @PathVariable("id") Long id) {
        try {
            List<Student> list = service.getAllStudentsByCourseId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new StudentDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(StudentDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .courseId(id)
                                    .students(StudentModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new StudentDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(StudentDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .students(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudentById(
            @PathVariable("id") Long id,
            @RequestBody StudentDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StudentDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .student(StudentModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentDtoResponse> deleteStudentById(
            @PathVariable(value = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StudentDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StudentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

}
