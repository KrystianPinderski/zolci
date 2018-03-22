package com.project.manager.admin;

import com.jfoenix.controls.JFXTreeTableView;
import com.project.manager.JavaFXThreadingRule;
import com.project.manager.controllers.AdminDashboardController;
import com.project.manager.entities.Project;
import com.project.manager.entities.UserModel;
import com.project.manager.models.ProjectViewInTable;
import com.project.manager.services.AdminService;
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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminDashboardTablesComponentTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    @Mock
    private AdminService adminService;

    @Mock
    private AdminDashboardController adminDashboardController;

    @InjectMocks
    private AdminDashboardTablesComponent adminDashboardTablesComponent;


    @Test
    public void generateProjectTableViewTest() {
        when(adminDashboardController.getProjectTable()).thenReturn(new JFXTreeTableView<>());
        when(adminService.getAllProjects()).thenReturn(getExampleProjects());

        adminDashboardTablesComponent.generateProjectTableView();

        assertNotNull(AdminDashboardTablesComponent.projectDTOObservableList);
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
                .id(1l)
                .projectName("projectOne")
                .projectInformation("project one inf")
                .manager(manager)
                .clients(clientList)
                .members(memberList)
                .build();

        Project projectTwo = Project.builder()
                .id(1l)
                .projectName("projectTwo")
                .projectInformation("project two inf")
                .manager(manager)
                .clients(clientList)
                .members(memberList)
                .build();

        return Arrays.asList(projectOne, projectTwo);
    }
}
