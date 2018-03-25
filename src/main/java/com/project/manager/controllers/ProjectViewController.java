package com.project.manager.controllers;

import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the class which is responsible for project view window.
 * This class perform display project information about selected project from dashboard
 */
@Component
public class ProjectViewController implements Initializable {

    @FXML
    private Button addUserButton;
    /**
     * Initialization of project view components
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUserButton.setOnAction(e -> {
            SceneManager sceneManager = SceneManager.getInstance();
            sceneManager.showInNewWindow(SceneType.ADD_USER);
        });
    }

}
//sceneManager.showScene(SceneType.REGISTRATION);