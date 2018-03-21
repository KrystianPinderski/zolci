package com.project.manager.models;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.entities.Project;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private JFXButton delete;

    public static ProjectDTO convert(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .firstAndLastName(project.getManager().getFirstName() + " " + project.getManager().getLastName())
                .countOfMembers(project.getMembers().size())
                .countOfClients(project.getClients().size())
                .build();
    }

    public ProjectDTO generateDelButton(ProjectDTO projectDTO) {
        JFXButton del = new JFXButton();
        Image image = new Image(getClass().getResourceAsStream("/images/delete.png"));
        del.setGraphic(new ImageView(image));
        projectDTO.setDelete(del);
        return projectDTO;
    }
}
