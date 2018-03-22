package com.project.manager.controllers.employee;

import com.jfoenix.controls.JFXButton;
import com.project.manager.models.TaskStatus;
import com.project.manager.services.SessionService;
import com.project.manager.ui.components.EmployeeTaskGenerator;
import com.project.manager.ui.sceneManager.SceneManager;
import com.project.manager.ui.sceneManager.SceneType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the class which is responsible for project view window.
 * This class perform display project information about selected project from dashboard
 */
@Component
public class EmployeeProjectViewController implements Initializable {

    @FXML
    private JFXButton backButton;

    @FXML
    private Label projectNameLabel;

    @FXML
    private VBox productBacklogVBox;

    @FXML
    private VBox sprintBacklogVBox;

    @FXML
    private VBox inProgressVBox;

    @FXML
    private VBox testingVBox;

    @FXML
    private VBox codeReviewVBox;

    @FXML
    private VBox doneVBox;

    private SessionService sessionService;
    private SceneManager sceneManager;
    private EmployeeTaskGenerator employeeTaskGenerator;

    @Autowired
    public EmployeeProjectViewController(EmployeeTaskGenerator employeeTaskGenerator) {
        this.sessionService = SessionService.getInstance();
        this.employeeTaskGenerator = employeeTaskGenerator;
        this.sceneManager = SceneManager.getInstance();
    }

    /**
     * Initialization of project view components
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectNameLabel.setText(sessionService.getProject().getProjectName());

        backButton.setOnAction(e -> {
            sceneManager.showScene(SceneType.DASHBOARD);
        });

        employeeTaskGenerator.inject(productBacklogVBox, TaskStatus.PRODUCT_BACKLOG);
        employeeTaskGenerator.inject(sprintBacklogVBox, TaskStatus.SPRINT_BACKLOG);
        employeeTaskGenerator.inject(inProgressVBox, TaskStatus.IN_PROGRESS);
        employeeTaskGenerator.inject(testingVBox, TaskStatus.TESTING);
        employeeTaskGenerator.inject(codeReviewVBox, TaskStatus.CODE_REVIEW);
        employeeTaskGenerator.inject(doneVBox, TaskStatus.DONE);
    }
}
