package com.project.manager.controllers;

import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    @FXML
    private Button register;
    private SceneManager sceneManager;

    @Autowired
    public LoginController() {
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register.setOnAction(e -> {
            sceneManager.showScene(SceneType.REGISTRATION);
        });
    }
}
