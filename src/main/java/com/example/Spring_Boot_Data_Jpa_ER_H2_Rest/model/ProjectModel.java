package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectModel {
    private Long id;
    private String name;

    public static ProjectModel getModel(Project project) {
        ProjectModel model = new ProjectModel();
        model.setId(project.getId());
        model.setName(project.getName());
        return model;
    }

    public static List<ProjectModel> getModel(List<Project> projectList) {
        List<ProjectModel> modelList = new ArrayList<>();
        if (projectList != null) {
            for (Project project : projectList) {
                ProjectModel model = new ProjectModel();
                model.setId(project.getId());
                model.setName(project.getName());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
