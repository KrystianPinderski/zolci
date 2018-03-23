package com.project.manager.admin;

import com.project.manager.admin.projects.AdminDashboardTablesComponentTest;
import com.project.manager.entities.Project;
import com.project.manager.repositories.ProjectRepository;
import com.project.manager.services.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    private List<Project> exampleProject;

    @InjectMocks
    private AdminService adminService;

    @Mock
    private ProjectRepository projectRepository;

    @Before
    public void setData() {
        AdminDashboardTablesComponentTest adminDashboardTablesComponentTest = new AdminDashboardTablesComponentTest();
        exampleProject = adminDashboardTablesComponentTest.getExampleProjects();
    }

    @Test
    public void getAllProjectsTest() {
        when(projectRepository.findAll()).thenReturn(exampleProject);

        adminService.getAllProjects();

        assertNotNull(exampleProject);
        assertEquals(exampleProject.size(), 2);

        assertNotNull(exampleProject.get(0));
        assertEquals(exampleProject.get(0).getId().intValue(), 1);
        assertEquals(exampleProject.get(0).getProjectName(), "projectOne");
        assertEquals(exampleProject.get(0).getManager(), exampleProject.get(0).getManager());
        assertEquals(exampleProject.get(0).getClients().size(), 1);
        assertEquals(exampleProject.get(0).getMembers().size(), 1);
    }
}
