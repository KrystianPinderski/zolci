package com.project.manager.ui.components;

import com.project.manager.controllers.employee.EmployeeTaskBrickComponent;
import com.project.manager.entities.Project;
import com.project.manager.models.TaskStatus;
import com.project.manager.repositories.ProjectRepository;
import com.project.manager.services.SessionService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmployeeTaskGenerator {
    private ProjectRepository projectRepository;
    private SessionService sessionService;

    @Autowired
    public EmployeeTaskGenerator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.sessionService = SessionService.getInstance();
    }

    public void inject(VBox box, TaskStatus taskStatus) {
        Project project = sessionService.getProject();
        project.getTasks().forEach(task -> {
            if (task.getTaskStatus() == taskStatus.ordinal()) {
                try {
                    AnchorPane taskBox;
                    EmployeeTaskBrickComponent employeeTaskBrickComponent = new EmployeeTaskBrickComponent();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/employee/employeeTaskBrickComponent.fxml"));
                    fxmlLoader.setController(employeeTaskBrickComponent);
                    taskBox = fxmlLoader.load();
                    employeeTaskBrickComponent.getNameLabel().setText(task.getName());
                    employeeTaskBrickComponent.getTagLabel().setText(task.getTag());
                    switch (task.getPriority()) {
                        case 1: {
                            employeeTaskBrickComponent.getPriorityColor().setStyle("-fx-background-color: #00FF00;");
                            break;
                        }
                        case 2: {
                            employeeTaskBrickComponent.getPriorityColor().setStyle("-fx-background-color: #ffff00;");
                            break;
                        }
                        case 3: {
                            employeeTaskBrickComponent.getPriorityColor().setStyle("-fx-background-color: #ff0000;");
                            break;
                        }
                    }
                    box.getChildren().add(taskBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
