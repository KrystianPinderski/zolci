package com.project.manager.controllers;

import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.entities.Project;
import com.project.manager.models.ProjectDTO;
import com.project.manager.services.AdminService;
import com.project.manager.ui.components.AdminDashboardTablesComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.print.attribute.standard.PresentationDirection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Getter
public class AdminDashboardController implements Initializable {

    private AdminService adminService;
    private AdminDashboardTablesComponent adminDashboardTablesComponent;

    @Autowired
    public AdminDashboardController(AdminService adminService, AdminDashboardTablesComponent adminDashboardTablesComponent) {
        this.adminService = adminService;
        this.adminDashboardTablesComponent = adminDashboardTablesComponent;
    }

    @FXML
    private Tab projectsTab;

    @FXML
    private Tab usersTab;

    @FXML
    private Tab inboxTab;

    @FXML
    private JFXTreeTableView<ProjectDTO> projectTable;

    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Project> projects = adminService.getAllProjects();
        adminDashboardTablesComponent.generateProjectTableView(projects);
    }
}
