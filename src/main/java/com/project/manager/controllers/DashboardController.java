package com.project.manager.controllers;

import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.services.ProjectService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private Button backToLogin;

    @FXML
    private Button testButton;

    @FXML
    private VBox projectsArea;

    private SceneManager sceneManager;

    private ProjectService projectService;

    @Autowired
    public DashboardController(ProjectService projectService) {
        this.projectService = projectService;
        sceneManager = SceneManager.getInstance();
    }


    public void createPane() {
        projectService.projectsOfUser().forEach(project -> {
            try {
                AnchorPane newAnchorPane;
                ProjectPaneController controller = new ProjectPaneController();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/projectPane.fxml"));
                fxmlLoader.setController(controller);
                newAnchorPane = fxmlLoader.load();
                controller.setProjectId(project.getId());
                controller.getProjectName().setText(project.getProjectName());
                projectsArea.getChildren().add(newAnchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //projectsArea = new VBox();
        createPane();

        backToLogin.setOnAction(e -> {
            sceneManager.showScene(SceneType.LOGIN);

        });

        testButton.setOnAction(e -> {
            projectService.projectsOfUser();

        });
    }
}
