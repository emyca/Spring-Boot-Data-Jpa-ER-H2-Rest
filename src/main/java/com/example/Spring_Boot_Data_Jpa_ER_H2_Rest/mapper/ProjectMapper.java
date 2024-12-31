package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ProjectDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project dtoCreateToEntity(ProjectDtoRequest request) {
        Project project = new Project();
        project.setId(request.id());
        project.setName(request.name());
        return project;
    }

    public Project dtoUpdateByIdToEntity(ProjectDtoRequest request,
                                         Project projectToUpdate) {
        projectToUpdate.setName(request.name());
        return projectToUpdate;
    }
}
