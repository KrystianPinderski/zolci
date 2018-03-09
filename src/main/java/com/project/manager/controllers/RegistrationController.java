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
public class RegistrationController implements Initializable{

    @FXML
    private Button testButton;
    private SceneManager sceneManager;

    @Autowired
    public RegistrationController() {
        sceneManager = SceneManager.getInstance();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       testButton.setOnAction(e ->
               sceneManager.showScene(SceneType.LOGIN)
       );
    }
}
