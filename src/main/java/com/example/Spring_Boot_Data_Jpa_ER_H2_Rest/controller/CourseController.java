package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.*;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Course;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.CourseModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {

    @Autowired
    @Qualifier("CourseServiceImpl")
    private CourseService service;

    @PostMapping("/courses")
    public ResponseEntity<CourseDtoResponse> createCourse(
            @RequestBody CourseDtoRequest request) {
        try {
            Course course = service.create(request);
            return (course != null) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CourseDtoResponse.Builder()
                                    .status(HttpStatus.CREATED.value())
                                    .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                    .success(true)
                                    .message(CourseDtoResponse
                                            .Message.SUCCESS_SAVE_MSG.getMessage())
                                    .course(CourseModel.getModel(course))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CourseDtoResponse.Builder()
                                    .status(HttpStatus.NO_CONTENT.value())
                                    .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                    .success(false)
                                    .message(CourseDtoResponse
                                            .Message.FAILURE_SAVE_MSG.getMessage())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/students/{id}/courses")
    public ResponseEntity<CourseDtoResponse> setCourseToByStudentId(
            @PathVariable("id") Long id,
            @RequestBody CourseDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CourseDtoResponse
                                    .Message.SUCCESS_SAVE_MSG.getMessage())
                            .course(CourseModel.getModel(
                                    service.setToByStudentId(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<CourseDtoResponse> getAllCourses() {
        List<Course> list = service.getAll();
        if (!list.isEmpty()) {
            List<CourseModel> _list = new ArrayList<>();
            for (Course course : list)
                _list.add(CourseModel.getModel(course));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CourseDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .courses(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(CourseDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .courses(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> getCourseById(
            @PathVariable("id") Long id) {
        try {
            Course course = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CourseDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .course(CourseModel.getModel(course))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/students/{id}/courses")
    public ResponseEntity<CourseDtoResponse> getAllCoursesByStudentId(
            @PathVariable("id") Long id) {
        try {
            List<Course> list = service.getAllCoursesByStudentId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CourseDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(CourseDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .studentId(id)
                                    .courses(CourseModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CourseDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(CourseDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .courses(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> updateCourseById(
            @PathVariable("id") Long id,
            @RequestBody CourseDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CourseDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .course(CourseModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public ResponseEntity<CourseDtoResponse> unsetCourseByIdAndStudentId(
            @PathVariable("studentId") Long studentId,
            @PathVariable("courseId") Long courseId) {
        try {
            service.unsetByIdFromStudentId(studentId, courseId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message("Course with id " + courseId + " has been unset " +
                                    "from Student with id " + studentId + ".")
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<CourseDtoResponse> deleteCourseById(
            @PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CourseDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CourseDtoResponse.Builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .success(false)
                            .message("Can't delete the Course with id " + id + ". " +
                                    "Referential integrity constraint violation. " +
                                    "First unset the Course from Student(s)!")
                            .build());
        }
    }
}
