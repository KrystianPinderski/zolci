package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.ui.components.admin.AdminDashboardTablesComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private SceneManager sceneManager;

    @Autowired
    public AdminDashboardController(AdminDashboardTablesComponent adminDashboardTablesComponent) {
        this.adminDashboardTablesComponent = adminDashboardTablesComponent;
        this.sceneManager = SceneManager.getInstance();
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
    private JFXButton updateProject;

    @FXML
    private JFXButton deleteProject;

    @FXML
    private JFXButton showProject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateProject.setOnAction(e -> sceneManager.showInNewWindow(SceneType.ADMIN_UPDATE_PROJECT));
        adminDashboardTablesComponent.generateProjectTableView(this);
    }
}
