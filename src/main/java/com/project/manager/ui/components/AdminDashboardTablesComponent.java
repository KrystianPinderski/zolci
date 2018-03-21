package com.project.manager.ui.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.services.AdminService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminDashboardTablesComponent {

    private ObservableList projectDTOObservableList;

    private AdminService adminService;

    @Autowired
    public AdminDashboardTablesComponent(AdminService adminService) {
        this.adminService = adminService;
    }

    public void generateProjectTableView(AdminDashboardController adminDashboardController) {
         List<Project> projects = adminService.getAllProjects();
         projectDTOObservableList = FXCollections
                .observableList(projects
                        .stream()
                        .map(ProjectViewInTable::convert)
                        .map(projectViewInTable -> projectViewInTable.generateDelButton(projectViewInTable))
                        .peek(projectViewInTable -> projectViewInTable.getDelete().setOnAction(e -> deleteProject(projectViewInTable.getId())))
                        .collect(Collectors.toList()));


        TreeTableColumn<ProjectViewInTable, CheckBox> checkColumn = new TreeTableColumn<>("");
        TreeTableColumn<ProjectViewInTable, String> projectNameColumn = new TreeTableColumn<>("Project name");
        TreeTableColumn<ProjectViewInTable, String> managerColumn = new TreeTableColumn<>("Manager");
        TreeTableColumn<ProjectViewInTable, Integer> countOfMembersColumn = new TreeTableColumn<>("Count of members");
        TreeTableColumn<ProjectViewInTable, Integer> countOfClientsColumn = new TreeTableColumn<>("Count of clients");
        TreeTableColumn<ProjectViewInTable, JFXButton> deleteButtonColumn = new TreeTableColumn<>("");


        adminDashboardController.getProjectTable().getColumns().addAll
                (checkColumn, projectNameColumn, managerColumn, countOfMembersColumn, countOfClientsColumn ,deleteButtonColumn);

        checkColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("check"));
        projectNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("projectName"));
        managerColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstAndLastName"));
        countOfMembersColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("countOfMembers"));
        countOfClientsColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("countOfClients"));
        deleteButtonColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("delete"));

        TreeItem<ProjectViewInTable> item = new RecursiveTreeItem<ProjectViewInTable>(projectDTOObservableList, RecursiveTreeObject::getChildren);

        adminDashboardController.getProjectTable().setRoot(item);
        adminDashboardController.getProjectTable().setShowRoot(false);
    }

    /**
     * To implemented
     * @param id
     */
    private void deleteProject(Long id) {
        System.out.println(id);
    }
}
