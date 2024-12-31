package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.CourseModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record CourseDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long studentId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        CourseModel course,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<CourseModel> courses) {

    private CourseDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.studentId,
                builder.course,
                builder.courses
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        Long studentId;
        CourseModel course;
        List<CourseModel> courses;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder reasonPhrase(String reasonPhrase) {
            this.reasonPhrase = reasonPhrase;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder studentId(Long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder course(CourseModel course) {
            this.course = course;
            return this;
        }

        public Builder courses(List<CourseModel> courses) {
            this.courses = courses;
            return this;
        }

        public CourseDtoResponse build() {
            return new CourseDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_SAVE_MSG("Course has been saved successfully."),
        FAILURE_SAVE_MSG("Course has not been saved."),
        SUCCESS_GET_LIST_MSG("Courses have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Courses have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Course with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Course with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Course with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Course with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
