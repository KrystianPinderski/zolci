package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.project.manager.ui.components.admin.AdminDashboardTablesComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdminUpdateProjectController implements Initializable{

    @FXML
    private JFXButton accept;

    @FXML
    private JFXButton cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accept.setOnAction(e -> AdminDashboardTablesComponent.
                projectDTOObservableList
                .filtered(projectViewInTable -> projectViewInTable.getCheck().isSelected())
                .forEach(projectViewInTable -> System.out.println(projectViewInTable.getId())));

    }
}
