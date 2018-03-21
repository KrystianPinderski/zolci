package com.project.manager.models;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
public class ProjectViewInTable extends RecursiveTreeObject<ProjectViewInTable> {

    private Long id;

    private String projectName;

    private String firstAndLastName;

    private int countOfMembers;

    private int countOfClients;

    private JFXButton delete;

    private JFXCheckBox check;

    public static ProjectViewInTable convert(Project project) {
        return ProjectViewInTable.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .firstAndLastName(project.getManager().getFirstName() + " " + project.getManager().getLastName())
                .countOfMembers(project.getMembers().size())
                .countOfClients(project.getClients().size())
                .check(new JFXCheckBox())
                .build();
    }

    public ProjectViewInTable generateDelButton(ProjectViewInTable projectViewInTable) {
        JFXButton del = new JFXButton();
        Image image = new Image(getClass().getResourceAsStream("/images/delete.png"));
        del.setGraphic(new ImageView(image));
        projectViewInTable.setDelete(del);
        return projectViewInTable;
    }
}
