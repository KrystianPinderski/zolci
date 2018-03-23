package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.models.ProjectTableView;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.ui.components.admin.AdminDashboardTablesComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is class which is responsible to handling the actions on AdminDashboard Windows, this class also
 * contains all references to tables and other JavaFX and JFeonix components which helps as to create interface
 */
@Component
@Getter
@Setter
public class AdminDashboardController implements Initializable {

    private AdminDashboardTablesComponent adminDashboardTablesComponent;
    private SceneManager sceneManager;

    /**
     * The constructor of this Spring Bean contains reference to {@link AdminDashboardTablesComponent},
     * this class are injected to this controller to manage components of AdminDashboard Window
     * Inside constructor is created reference to SessionManager {@link SceneManager} to switch some other
     * windows
     * @param adminDashboardTablesComponent
     */
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
    private JFXTreeTableView<ProjectTableView> projectTable;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton updateProject;

    @FXML
    private JFXButton deleteProject;

    @FXML
    private JFXButton showProject;

    /**
     * This method is responsible for listening the controller in window, and making action
     * implemented in lambdas expression
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminDashboardTablesComponent.generateProjectTableView();
        projectsTab.setOnSelectionChanged(e -> adminDashboardTablesComponent.generateTables());
        updateProject.setOnAction(e -> sceneManager.showInNewWindow(SceneType.ADMIN_UPDATE_PROJECT));
    }
}
