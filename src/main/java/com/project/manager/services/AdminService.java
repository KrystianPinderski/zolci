package com.project.manager.services;

import com.project.manager.entities.Project;
import com.project.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private ProjectRepository projectRepository;

    @Autowired
    public AdminService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * To implement
     * @param id
     */
    public void deleteProject(Long id) {
    }
}
