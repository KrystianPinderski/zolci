package com.project.manager.ui.components.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.models.ProjectTableView;
import com.project.manager.services.ProjectService;
import javafx.beans.property.SimpleLongProperty;
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
    public static ObservableList<ProjectTableView> projectDTOObservableList;
//    public static ObservableList<>

    private ProjectService projectService;
    private AdminDashboardController adminDashboardController;

    /**
     * Constructor of spring bean with two beans injected
     * @param projectService this bean is responsible for every logic operation of projects
     * @param adminDashboardController this controller is inject to send some component references
     */
    @Autowired
    public AdminDashboardTablesComponent(ProjectService projectService, @Lazy AdminDashboardController adminDashboardController) {
        this.projectService = projectService;
        this.adminDashboardController = adminDashboardController;
    }

    public void generateTables() {
        if (adminDashboardController.getProjectsTab().isSelected() &&
                adminDashboardController.getProjectTable().getCurrentItemsCount() < 1) {
            generateProjectTableView();
        }
    }
    /**
     * This method get table of projects from Admin dashboard view and generate table with provided project form DB
     */
    public void generateProjectTableView() {
         adminDashboardController.getProjectTable().setSelectionModel(null);

         List<Project> projects = projectService.getAllProjects();
         projectDTOObservableList = FXCollections
                .observableList(projects
                        .stream()
                        .map(ProjectTableView::convert)
                        .map(projectTableView -> projectTableView.generateDelButton(projectTableView))
                        .peek(projectTableView -> projectTableView.getDelete().getValue().setOnAction(e -> projectService.deleteProject(projectTableView.getId().get())))
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
                (checkColumn, idColumn, projectNameColumn, managerColumn, clientColumn ,countOfMembersColumn, deleteButtonColumn);

        checkColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getCheck().get()));
        idColumn.setCellValueFactory(p -> p.getValue().getValue().getId().asObject());
        projectNameColumn.setCellValueFactory(p -> p.getValue().getValue().getProjectName());
        managerColumn.setCellValueFactory(p -> p.getValue().getValue().getManagerFirstAndLastName());
        clientColumn.setCellValueFactory(p -> p.getValue().getValue().getClientFirstAndLastName());
        countOfMembersColumn.setCellValueFactory(p -> p.getValue().getValue().getCountOfMembers().asObject());
        deleteButtonColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getDelete().get()));

        TreeItem<ProjectTableView> item = new RecursiveTreeItem<ProjectTableView>(projectDTOObservableList, RecursiveTreeObject::getChildren);

        adminDashboardController.getProjectTable().setRoot(item);
        adminDashboardController.getProjectTable().setShowRoot(false);
    }


}
