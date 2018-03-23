package com.project.manager.admin.projects;

import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.JavaFXThreadingRule;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.entities.UserModel;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.services.ProjectService;
import com.project.manager.ui.components.admin.AdminDashboardTablesComponent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminDashboardTablesComponentTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    @Mock
    private ProjectService projectService;

    @Mock
    private AdminDashboardController adminDashboardController;

    @InjectMocks
    private AdminDashboardTablesComponent adminDashboardTablesComponent;


    @Test
    public void generateProjectTableViewTest() {
        JFXTreeTableView<ProjectViewInTable> table = new JFXTreeTableView<>();
        when(adminDashboardController.getProjectTable()).thenReturn(table);
        when(projectService.getAllProjects()).thenReturn(getExampleProjects());

        adminDashboardTablesComponent.generateProjectTableView();


        assertNotNull(AdminDashboardTablesComponent.projectDTOObservableList);
        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.size(), 2);

        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.get(0).getId().getValue(),
                getExampleProjects().get(0).getId());

        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.get(0).getProjectName().getValue(),
                getExampleProjects().get(0).getProjectName());

        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.get(0).getFirstAndLastName().getValue(),
                getExampleProjects().get(0).getManager().getFirstName() + " "
                        + getExampleProjects().get(0).getManager().getLastName());

        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.get(0).getCountOfMembers().get(),
                getExampleProjects().get(0).getMembers().size());

        assertEquals(AdminDashboardTablesComponent.projectDTOObservableList.get(0).getCountOfClients().get(),
                getExampleProjects().get(0).getClients().size());


        assertEquals(table.getColumns().size(), 6);
        assertNotNull(table.getColumns().get(1).getCellObservableValue(0).getValue());
        assertEquals(table.getColumns().get(1).getCellObservableValue(0).getValue(), "projectOne");
        assertEquals(table.getColumns().get(2).getCellObservableValue(0).getValue(), "Adam Manager");
        assertEquals(table.getColumns().get(3).getCellObservableValue(0).getValue(), 1);
        assertEquals(table.getColumns().get(4).getCellObservableValue(0).getValue(), 1);
            }

    public List<Project> getExampleProjects() {
        UserModel manager = UserModel.builder()
                .id(1l)
                .firstName("Adam")
                .lastName("Manager")
                .projectsAsManager(new HashSet<>())
                .build();
        UserModel member = UserModel.builder()
                .id(2l)
                .firstName("Adam")
                .lastName("Member")
                .projectsAsManager(new HashSet<>())
                .build();

        UserModel client = UserModel.builder()
                .id(2l)
                .firstName("Adam")
                .lastName("Client")
                .projectsAsClient(new HashSet<>())
                .build();

        Set<UserModel> clientList = Sets.newSet(client);
        Set<UserModel> memberList = Sets.newSet(member);

        Project projectOne = Project.builder()
                .id(1L)
                .projectName("projectOne")
                .projectInformation("project one inf")
                .manager(manager)
                .clients(clientList)
                .members(memberList)
                .build();

        Project projectTwo = Project.builder()
                .id(1L)
                .projectName("projectTwo")
                .projectInformation("project two inf")
                .manager(manager)
                .clients(clientList)
                .members(memberList)
                .build();

        return Arrays.asList(projectOne, projectTwo);
    }
}
