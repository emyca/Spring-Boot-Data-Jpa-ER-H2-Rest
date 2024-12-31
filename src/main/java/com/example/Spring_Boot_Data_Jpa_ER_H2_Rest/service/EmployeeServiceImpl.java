package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.EmployeeMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.EmployeeRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Employee create(EmployeeDtoRequest request) {
        return employeeRepository.save(
                employeeMapper.dtoCreateToEntity(request));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id)
            throws ResourceException {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Employee with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public List<Employee> getAllEmployeesByProjectId(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceException(
                    "Project with id %s has not been found!"
                            .formatted(projectId));
        }
        return employeeRepository.findEmployeesByProjectsId(projectId);
    }

    @Override
    public Employee updateById(Long id, EmployeeDtoRequest request)
            throws ResourceException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Employee with id %s has not been found!"
                                        .formatted(id)));
        return employeeRepository.save(employeeMapper
                .dtoUpdateByIdToEntity(request, employee));
    }

    @Override
    public void deleteById(Long id)
            throws ResourceException {
        employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Employee with id %s has not been found!"
                                        .formatted(id)));
        employeeRepository.deleteById(id);
    }
}
