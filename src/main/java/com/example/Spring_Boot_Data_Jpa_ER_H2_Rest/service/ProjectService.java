package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;

import java.util.List;

public interface ProjectService {
    Project create(ProjectDtoRequest request);
    Project setToByEmployeeId(Long id, ProjectDtoRequest request);
    List<Project> getAll();
    Project getById(Long id);
    Project updateById(Long id, ProjectDtoRequest request);
    void unsetByIdFromEmployeeId(Long employeeId, Long projectId);
    void deleteById(Long id);
}
