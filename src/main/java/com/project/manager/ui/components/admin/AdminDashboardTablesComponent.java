package com.project.manager.ui.components.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.entities.UserModel;
import com.project.manager.models.ProjectTableView;
import com.project.manager.models.UserTableView;
import com.project.manager.services.ProjectService;
import com.project.manager.services.UserService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will manage the components of AdminDashboardController
 */
@Component
public class AdminDashboardTablesComponent {

    /**
     * List of Projects in service
     */
    public static ObservableList<ProjectTableView> projectTableViews;
    public static ObservableList<UserTableView> userTableViews;

    private ProjectService projectService;
    private UserService userService;
    private AdminDashboardController adminDashboardController;

    /**
     * Constructor of spring bean with two beans injected
     *
     * @param projectService           this bean is responsible for every logic operation of projects
     * @param adminDashboardController this controller is inject to send some component references
     */
    @Autowired
    public AdminDashboardTablesComponent(ProjectService projectService,
                                         UserService userService,
                                         @Lazy AdminDashboardController adminDashboardController) {
        this.projectService = projectService;
        this.userService = userService;
        this.adminDashboardController = adminDashboardController;
    }

    public void generateTables() {
        if (adminDashboardController.getProjectsTab().isSelected() &&
                adminDashboardController.getProjectTable().getCurrentItemsCount() < 1) {
            generateProjectTableView();
        }
        if (adminDashboardController.getUsersTab().isSelected() &&
                adminDashboardController.getUserTable().getCurrentItemsCount() < 1) {
            generateUserTableView();
        }
    }

    private void generateUserTableView() {
        adminDashboardController.getUserTable().getColumns().clear();
        adminDashboardController.getUserTable().setSelectionModel(null);

        List<UserModel> users = userService.getAllUsers();
        userTableViews = FXCollections.
                observableList(users
                        .stream()
                        .map(UserTableView::convert)
                        .map(u -> u.generateDelButton(u))
                        .peek(u -> u.getDelete().getValue()
                                .setOnAction(e -> userService.delete(u.getId().get())))
                        .map(u -> u.generateLockOrUnlockButton(u))
                        .peek(u -> u.getLockOrUnlock().getValue()
                                .setOnAction(e -> {
                                    userService.changeLockStatus(u.getIsLocked().get(), u.getId().get());
                                    generateUserTableView();
                                }))
                        .map(u -> u.generateResetButton(u))
                        .peek(u -> u.getResetPass().getValue()
                                .setOnAction(e -> userService.changePassword(u.getId().get())))
                        .collect(Collectors.toList()));

        TreeTableColumn<UserTableView, CheckBox> checkColumn = new TreeTableColumn<>("");
        TreeTableColumn<UserTableView, Long> idColumn = new TreeTableColumn<>("Id");
        TreeTableColumn<UserTableView, String> usernameColumn = new TreeTableColumn<>("Username");
        TreeTableColumn<UserTableView, String> emailColumn = new TreeTableColumn<>("Email");
        TreeTableColumn<UserTableView, String> roleColumn = new TreeTableColumn<>("Role");
        TreeTableColumn<UserTableView, Integer> countOfProjectsColumn = new TreeTableColumn<>("Count of projects");
        TreeTableColumn<UserTableView, Boolean> lockColumn = new TreeTableColumn<>("Lock");
        TreeTableColumn<UserTableView, JFXButton> lockButtonColumn = new TreeTableColumn<>("");
        TreeTableColumn<UserTableView, JFXButton> resetPassColumn = new TreeTableColumn<>("");
        TreeTableColumn<UserTableView, JFXButton> deleteColumn = new TreeTableColumn<>("");

        checkColumn.setSortable(false);
        idColumn.setSortable(false);
        usernameColumn.setSortable(false);
        emailColumn.setSortable(false);
        roleColumn.setSortable(false);
        countOfProjectsColumn.setSortable(false);
        lockColumn.setSortable(false);
        lockButtonColumn.setSortable(false);
        resetPassColumn.setSortable(false);
        deleteColumn.setSortable(false);


        adminDashboardController.getUserTable().getColumns().addAll
                (checkColumn, idColumn, usernameColumn, emailColumn, roleColumn, countOfProjectsColumn, lockColumn, lockButtonColumn, resetPassColumn, deleteColumn);

        checkColumn.setCellValueFactory(u -> new SimpleObjectProperty(u.getValue().getValue().getCheck().get()));
        idColumn.setCellValueFactory(u -> u.getValue().getValue().getId().asObject());
        usernameColumn.setCellValueFactory(u -> u.getValue().getValue().getUsername());
        emailColumn.setCellValueFactory(u -> u.getValue().getValue().getEmail());
        roleColumn.setCellValueFactory(u -> u.getValue().getValue().getRole());
        countOfProjectsColumn.setCellValueFactory(u -> u.getValue().getValue().getCountOfProjects().asObject());
        lockColumn.setCellValueFactory(u -> u.getValue().getValue().getIsLocked());
        lockButtonColumn.setCellValueFactory(u -> new SimpleObjectProperty(u.getValue().getValue().getLockOrUnlock().get()));
        resetPassColumn.setCellValueFactory(u -> new SimpleObjectProperty(u.getValue().getValue().getResetPass().get()));
        deleteColumn.setCellValueFactory(u -> new SimpleObjectProperty(u.getValue().getValue().getDelete().get()));

        TreeItem<UserTableView> item = new RecursiveTreeItem<UserTableView>(userTableViews, RecursiveTreeObject::getChildren);

        adminDashboardController.getUserTable().setRoot(item);
        adminDashboardController.getUserTable().setShowRoot(false);
    }

    /**
     * This method get table of projects from Admin dashboard view and generate table with provided project form DB
     */
    public void generateProjectTableView() {
        adminDashboardController.getProjectTable().getColumns().clear();
        adminDashboardController.getProjectTable().setSelectionModel(null);

        List<Project> projects = projectService.getAllProjects();
        projectTableViews = FXCollections
                .observableList(projects
                        .stream()
                        .map(ProjectTableView::convert)
                        .map(projectTableView -> projectTableView.generateDelButton(projectTableView))
                        .peek(projectTableView -> projectTableView.getDelete().getValue()
                                .setOnAction(e -> projectService.delete(projectTableView.getId().get())))
                        .collect(Collectors.toList()));


        TreeTableColumn<ProjectTableView, CheckBox> checkColumn = new TreeTableColumn<>("");
        TreeTableColumn<ProjectTableView, Long> idColumn = new TreeTableColumn<>("Id");
        TreeTableColumn<ProjectTableView, String> projectNameColumn = new TreeTableColumn<>("Project name");
        TreeTableColumn<ProjectTableView, String> managerColumn = new TreeTableColumn<>("Manager");
        TreeTableColumn<ProjectTableView, String> clientColumn = new TreeTableColumn<>("Client");
        TreeTableColumn<ProjectTableView, Integer> countOfMembersColumn = new TreeTableColumn<>("Count of members");
        TreeTableColumn<ProjectTableView, JFXButton> deleteButtonColumn = new TreeTableColumn<>("");

        checkColumn.setSortable(false);
        idColumn.setSortable(false);
        projectNameColumn.setSortable(false);
        managerColumn.setSortable(false);
        countOfMembersColumn.setSortable(false);
        clientColumn.setSortable(false);
        deleteButtonColumn.setSortable(false);


        adminDashboardController.getProjectTable().getColumns().addAll
                (checkColumn, idColumn, projectNameColumn, managerColumn, clientColumn, countOfMembersColumn, deleteButtonColumn);

        checkColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getCheck().get()));
        idColumn.setCellValueFactory(p -> p.getValue().getValue().getId().asObject());
        projectNameColumn.setCellValueFactory(p -> p.getValue().getValue().getProjectName());
        managerColumn.setCellValueFactory(p -> p.getValue().getValue().getManagerFirstAndLastName());
        clientColumn.setCellValueFactory(p -> p.getValue().getValue().getClientFirstAndLastName());
        countOfMembersColumn.setCellValueFactory(p -> p.getValue().getValue().getCountOfMembers().asObject());
        deleteButtonColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getDelete().get()));

        TreeItem<ProjectTableView> item = new RecursiveTreeItem<ProjectTableView>(projectTableViews, RecursiveTreeObject::getChildren);

        adminDashboardController.getProjectTable().setRoot(item);
        adminDashboardController.getProjectTable().setShowRoot(false);
    }


}
