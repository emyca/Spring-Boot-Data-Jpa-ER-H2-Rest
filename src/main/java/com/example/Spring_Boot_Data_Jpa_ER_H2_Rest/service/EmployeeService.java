package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(EmployeeDtoRequest request);
    List<Employee> getAll();
    Employee getById(Long id);
    List<Employee> getAllEmployeesByProjectId(Long projectId);
    Employee updateById(Long id, EmployeeDtoRequest request);
    void deleteById(Long id);
}
