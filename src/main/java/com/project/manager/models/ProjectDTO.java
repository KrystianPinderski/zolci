package com.project.manager.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.entities.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProjectDTO extends RecursiveTreeObject<ProjectDTO> {

    private Long id;

    private String projectName;

    private String firstAndLastName;

    private int countOfMembers;

    private int countOfClients;

    public static ProjectDTO convert(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .firstAndLastName(project.getManager().getFirstName() + " " + project.getManager().getLastName())
                .countOfMembers(project.getMembers().size())
                .countOfClients(project.getClients().size())
                .build();
    }
}
