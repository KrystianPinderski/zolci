package com.project.manager.models;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.entities.Project;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProjectViewInTable extends RecursiveTreeObject<ProjectViewInTable> {

    private LongProperty id;

    private StringProperty projectName;

    private StringProperty firstAndLastName;

    private IntegerProperty countOfMembers;

    private IntegerProperty countOfClients;

    private SimpleObjectProperty<JFXButton> delete;

    private SimpleObjectProperty<JFXCheckBox> check;

    public static ProjectViewInTable convert(Project project) {
        return ProjectViewInTable.builder()
                .id(new SimpleLongProperty(project.getId()))
                .projectName(new SimpleStringProperty(project.getProjectName()))
                .firstAndLastName(new SimpleStringProperty(project.getManager().getFirstName() + " " + project.getManager().getLastName()))
                .countOfMembers(new SimpleIntegerProperty(project.getMembers().size()))
                .countOfClients(new SimpleIntegerProperty(project.getClients().size()))
                .check(new SimpleObjectProperty<>(new JFXCheckBox()))
                .build();
    }

    public ProjectViewInTable generateDelButton(ProjectViewInTable projectViewInTable) {
        JFXButton del = new JFXButton();
        Image image = new Image(getClass().getResourceAsStream("/images/delete.png"));
        del.setGraphic(new ImageView(image));
        projectViewInTable.setDelete(new SimpleObjectProperty<>(del));
        return projectViewInTable;
    }
}
