package com.project.manager.ui.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.models.ProjectDTO;
import com.project.manager.services.AdminService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                        .map(ProjectDTO::convert)
                        .map(projectDTO -> projectDTO.generateDelButton(projectDTO))
                        .peek(projectDTO -> projectDTO.getDelete().setOnAction(e -> deleteProject(projectDTO.getId())))
                        .collect(Collectors.toList()));


        TreeTableColumn<ProjectDTO, Long> idColumn = new TreeTableColumn<>("Id");
        TreeTableColumn<ProjectDTO, String> projectNameColumn = new TreeTableColumn<>("Project name");
        TreeTableColumn<ProjectDTO, String> managerColumn = new TreeTableColumn<>("Manager");
        TreeTableColumn<ProjectDTO, Integer> countOfMembersColumn = new TreeTableColumn<>("Count of members");
        TreeTableColumn<ProjectDTO, Integer> countOfClientsColumn = new TreeTableColumn<>("Count of clients");
        TreeTableColumn<ProjectDTO, JFXButton> deleteButtonColumn = new TreeTableColumn<>("");


        adminDashboardController.getProjectTable().getColumns().addAll
                (idColumn, projectNameColumn, managerColumn, countOfMembersColumn, countOfClientsColumn ,deleteButtonColumn);

        idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        projectNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("projectName"));
        managerColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstAndLastName"));
        countOfMembersColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("countOfMembers"));
        countOfClientsColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("countOfClients"));
        deleteButtonColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("delete"));

        TreeItem<ProjectDTO> item = new RecursiveTreeItem<ProjectDTO>(projectDTOObservableList, RecursiveTreeObject::getChildren);

        adminDashboardController.getProjectTable().setRoot(item);
        adminDashboardController.getProjectTable().setShowRoot(false);
    }

    private void deleteProject(Long id) {
        System.out.println(id);
    }
}
