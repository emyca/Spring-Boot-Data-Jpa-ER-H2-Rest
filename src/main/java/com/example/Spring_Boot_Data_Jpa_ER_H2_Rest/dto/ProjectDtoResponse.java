package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ProjectModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record ProjectDtoResponse(
        int status,
        String reasonPhrase,
        boolean success,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long employeeId,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        ProjectModel project,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<ProjectModel> projects) {

    private ProjectDtoResponse(Builder builder) {
        this(
                builder.status,
                builder.reasonPhrase,
                builder.success,
                builder.message,
                builder.employeeId,
                builder.project,
                builder.projects
        );
    }

    public static class Builder {
        int status;
        String reasonPhrase;
        boolean success;
        String message;
        Long employeeId;
        ProjectModel project;
        List<ProjectModel> projects;

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

        public Builder employeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder project(ProjectModel project) {
            this.project = project;
            return this;
        }

        public Builder projects(List<ProjectModel> projects) {
            this.projects = projects;
            return this;
        }

        public ProjectDtoResponse build() {
            return new ProjectDtoResponse(this);
        }
    }

    public enum Message {

        SUCCESS_SAVE_MSG("Project has been saved successfully."),
        FAILURE_SAVE_MSG("Project has not been saved."),
        SUCCESS_GET_LIST_MSG("Projects have been fetched successfully."),
        FAILURE_GET_LIST_MSG("Projects have not been found!"),
        SUCCESS_GET_BY_ID_MSG("Project with id %s has been fetched successfully."),
        FAILURE_GET_BY_ID_MSG("Project with id %s has not been found!"),
        SUCCESS_UPDATE_BY_ID_MSG("Project with id %s has been updated successfully."),
        SUCCESS_DELETE_BY_ID_MSG("Project with id %s has been deleted successfully.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
