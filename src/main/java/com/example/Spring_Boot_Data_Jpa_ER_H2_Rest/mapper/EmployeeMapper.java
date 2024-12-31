package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.EmployeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee dtoCreateToEntity(EmployeeDtoRequest request) {
        Employee employee = new Employee();
        employee.setId(request.id());
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        return employee;
    }

    public Employee dtoUpdateByIdToEntity(EmployeeDtoRequest request,
                                          Employee employeeToUpdate) {
        employeeToUpdate.setFirstName(request.firstName());
        employeeToUpdate.setLastName(request.lastName());
        employeeToUpdate.setEmail(request.email());
        return employeeToUpdate;
    }
}
