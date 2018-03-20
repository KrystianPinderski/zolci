package com.project.manager.controllers;

import com.project.manager.services.AdminService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Getter
public class AdminDashboardController implements Initializable {

    private AdminService adminService;

    @Autowired
    public AdminDashboardController(AdminService adminService) {
        this.adminService = adminService;
    }

    @FXML
    private Tab projectsTab;

    @FXML
    private Tab usersTab;

    @FXML
    private Tab inboxTab;

    @FXML
    private TableView<?> projectTable;

    @FXML
    private Button logoutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(adminService.getAllProjects());
    }
}
