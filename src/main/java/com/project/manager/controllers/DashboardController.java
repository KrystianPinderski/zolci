package com.project.manager.controllers;

import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.services.ProjectService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {

    @FXML
    private Button backToLogin;

    @FXML
    private Button testButton;

    private SceneManager sceneManager;

    private ProjectService projectService;

    @Autowired
    public DashboardController(ProjectService projectService) {
        this.projectService = projectService;
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backToLogin.setOnAction(e -> {
            sceneManager.showScene(SceneType.LOGIN);

        });

        testButton.setOnAction(e -> {
            projectService.projectsOfUser();
        });
    }
}
