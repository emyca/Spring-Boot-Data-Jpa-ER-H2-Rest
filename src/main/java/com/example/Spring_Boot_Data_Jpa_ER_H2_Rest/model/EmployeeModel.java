package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Project> projects;

    public static EmployeeModel getModel(Employee employee) {
        EmployeeModel model = new EmployeeModel();
        model.setId(employee.getId());
        model.setFirstName(employee.getFirstName());
        model.setLastName(employee.getLastName());
        model.setEmail(employee.getEmail());
        List<Project> projectList = new ArrayList<>();
        model.setProjects(projectList);
        List<Project> _projectList = employee.getProjects();
        if (_projectList != null) {
            for (Project project : _projectList) {
                Project _project = new Project();
                _project.setId(project.getId());
                _project.setName(project.getName());
                model.getProjects().add(_project);
            }
            model.setProjects(model.getProjects());
        }
        return model;
    }

    public static List<EmployeeModel> getModel(List<Employee> employeeList) {
        List<EmployeeModel> modelList = new ArrayList<>();
        if (employeeList != null) {
            for (Employee employee : employeeList) {
                EmployeeModel model = new EmployeeModel();
                model.setId(employee.getId());
                model.setFirstName(employee.getFirstName());
                model.setLastName(employee.getLastName());
                model.setEmail(employee.getEmail());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
