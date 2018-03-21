package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.ui.components.AdminDashboardTablesComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Getter
public class AdminDashboardController implements Initializable {

    private AdminDashboardTablesComponent adminDashboardTablesComponent;

    @Autowired
    public AdminDashboardController(AdminDashboardTablesComponent adminDashboardTablesComponent) {
        this.adminDashboardTablesComponent = adminDashboardTablesComponent;
    }

    @FXML
    private Tab projectsTab;

    @FXML
    private Tab usersTab;

    @FXML
    private Tab inboxTab;

    @FXML
    private JFXTreeTableView<ProjectViewInTable> projectTable;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton showButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDashboardTablesComponent.generateProjectTableView(this);
    }
}
