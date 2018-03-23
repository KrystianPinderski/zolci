package com.project.manager.ui.components.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.services.ProjectService;
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
 * This class is bridge between {@link AdminService} and {@link AdminDashboardController}
 * admin controller send all component reference to this class to get some values from them
 * or you just manage them
 */
@Component
public class AdminDashboardTablesComponent {

    /**
     * List of Projects in service
     */
    public static ObservableList<ProjectViewInTable> projectDTOObservableList;

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

    /**
     * This method get table of projects from Admin dashboard view and generate table with provided project form DB
     */
    public void generateProjectTableView() {
         adminDashboardController.getProjectTable().setSelectionModel(null);

         List<Project> projects = projectService.getAllProjects();
         projectDTOObservableList = FXCollections
                .observableList(projects
                        .stream()
                        .map(ProjectViewInTable::convert)
                        .map(projectViewInTable -> projectViewInTable.generateDelButton(projectViewInTable))
                        .peek(projectViewInTable -> projectViewInTable.getDelete().getValue().setOnAction(e -> projectService.deleteProject(projectViewInTable.getId().get())))
                        .collect(Collectors.toList()));


        TreeTableColumn<ProjectViewInTable, CheckBox> checkColumn = new TreeTableColumn<>("");
        TreeTableColumn<ProjectViewInTable, String> projectNameColumn = new TreeTableColumn<>("Project name");
        TreeTableColumn<ProjectViewInTable, String> managerColumn = new TreeTableColumn<>("Manager");
        TreeTableColumn<ProjectViewInTable, Integer> countOfMembersColumn = new TreeTableColumn<>("Count of members");
        TreeTableColumn<ProjectViewInTable, Integer> countOfClientsColumn = new TreeTableColumn<>("Count of clients");
        TreeTableColumn<ProjectViewInTable, JFXButton> deleteButtonColumn = new TreeTableColumn<>("");

        checkColumn.setSortable(false);
        projectNameColumn.setSortable(false);
        managerColumn.setSortable(false);
        countOfMembersColumn.setSortable(false);
        countOfClientsColumn.setSortable(false);
        deleteButtonColumn.setSortable(false);


        adminDashboardController.getProjectTable().getColumns().addAll
                (checkColumn, projectNameColumn, managerColumn, countOfMembersColumn, countOfClientsColumn ,deleteButtonColumn);

        checkColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getCheck().get()));
        projectNameColumn.setCellValueFactory(p -> p.getValue().getValue().getProjectName());
        managerColumn.setCellValueFactory(p -> p.getValue().getValue().getFirstAndLastName());
        countOfMembersColumn.setCellValueFactory(p -> p.getValue().getValue().getCountOfMembers().asObject());
        countOfClientsColumn.setCellValueFactory(p -> p.getValue().getValue().getCountOfClients().asObject());
        deleteButtonColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue().getDelete().get()));

        TreeItem<ProjectViewInTable> item = new RecursiveTreeItem<ProjectViewInTable>(projectDTOObservableList, RecursiveTreeObject::getChildren);

        adminDashboardController.getProjectTable().setRoot(item);
        adminDashboardController.getProjectTable().setShowRoot(false);
    }
}
