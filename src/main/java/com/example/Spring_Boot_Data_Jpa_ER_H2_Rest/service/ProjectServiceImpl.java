package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.ProjectMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.EmployeeRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Project create(ProjectDtoRequest request) {
        String name = request.name();
        if (projectRepository.findProjectByName(name) != null)
            throw new ResourceException(
                    "Project with name '%s' already exists!"
                            .formatted(name));
        else return projectRepository.save(
                projectMapper.dtoCreateToEntity(request));
    }

    @Override
    public Project setToByEmployeeId(Long id, ProjectDtoRequest request)
            throws ResourceException {
        return employeeRepository.findById(id).map(employee -> {
            long projectId = request.id();
            // Project існує
            if (projectId != 0L) {
                Project project = projectRepository.findById(projectId)
                        .orElseThrow(() ->
                                new ResourceException(
                                        "Project with id %s has not been found!"
                                                .formatted(projectId)));
                employee.addProject(project);
                employeeRepository.save(employee);
                return project;
            }
            // Додавання та створення нового Project
            Project _project =
                    projectMapper.dtoCreateToEntity(request);
            employee.addProject(_project);
            return projectRepository.save(_project);
        }).orElseThrow(() ->
                new ResourceException(
                        "Employee with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(Long id)
            throws ResourceException {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Project with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public Project updateById(Long id, ProjectDtoRequest request)
            throws ResourceException {
        Project employee = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Project with id %s has not been found!"
                                        .formatted(id)));
        return projectRepository.save(projectMapper
                .dtoUpdateByIdToEntity(request, employee));
    }

    @Override
    public void unsetByIdFromEmployeeId(Long employeeId, Long projectId)
            throws ResourceException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceException(
                                "Employee with id %s has not been found!"
                                        .formatted(employeeId)));
        employee.removeProject(projectId);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException,
            DataIntegrityViolationException {
        projectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Project with id %s has not been found!"
                                        .formatted(id)));
        // Can throw DataIntegrityViolationException
        projectRepository.deleteById(id);
    }
}
