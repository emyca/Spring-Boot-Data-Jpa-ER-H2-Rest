package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ProjectDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ProjectModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.ProjectService;
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
public class ProjectController {

    @Autowired
    @Qualifier("ProjectServiceImpl")
    private ProjectService service;

    @PostMapping("/projects")
    public ResponseEntity<ProjectDtoResponse> createProject(
            @RequestBody ProjectDtoRequest request) {
        try {
            Project project = service.create(request);
            return (project != null) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new ProjectDtoResponse.Builder()
                                    .status(HttpStatus.CREATED.value())
                                    .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                    .success(true)
                                    .message(ProjectDtoResponse
                                            .Message.SUCCESS_SAVE_MSG.getMessage())
                                    .project(ProjectModel.getModel(project))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new ProjectDtoResponse.Builder()
                                    .status(HttpStatus.NO_CONTENT.value())
                                    .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                    .success(false)
                                    .message(ProjectDtoResponse
                                            .Message.FAILURE_SAVE_MSG.getMessage())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/employees/{id}/projects")
    public ResponseEntity<ProjectDtoResponse> setProjectToByEmployeeId(
            @PathVariable("id") Long id,
            @RequestBody ProjectDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ProjectDtoResponse
                                    .Message.SUCCESS_SAVE_MSG.getMessage())
                            .employeeId(id)
                            .project(ProjectModel.getModel(
                                    service.setToByEmployeeId(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<ProjectDtoResponse> getAllProjects() {
        List<Project> list = service.getAll();
        if (!list.isEmpty()) {
            List<ProjectModel> _list = new ArrayList<>();
            for (Project project : list)
                _list.add(ProjectModel.getModel(project));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ProjectDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .projects(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ProjectDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .projects(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> getProjectById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ProjectDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .project(ProjectModel.getModel(service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> updateProjectById(
            @PathVariable("id") Long id,
            @RequestBody ProjectDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ProjectDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .project(ProjectModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/employees/{employeeId}/projects/{projectId}")
    public ResponseEntity<ProjectDtoResponse> unsetProjectByIdAndEmployeeId(
            @PathVariable("employeeId") Long employeeId,
            @PathVariable("projectId") Long projectId) {
        try {
            service.unsetByIdFromEmployeeId(employeeId, projectId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message("Project with id " + projectId + " has been unset " +
                                    "from Employee with id " + employeeId + ".")
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<ProjectDtoResponse> deleteProjectById(
            @PathVariable(value = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ProjectDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProjectDtoResponse.Builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .success(false)
                            .message("Can't delete the Project with id " + id + ". " +
                                    "Referential integrity constraint violation. " +
                                    "First unset the Project from Employee(s)!")
                            .build());
        }
    }
}
