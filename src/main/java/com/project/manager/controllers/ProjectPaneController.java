package com.project.manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class ProjectPaneController implements Initializable {

    private Long projectId;

    @FXML
    private Label projectName;

    @FXML
    private Button viewProject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewProject.setOnAction(e -> System.out.println(projectId));

    }
}
