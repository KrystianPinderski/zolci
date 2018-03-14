package com.project.manager.services;

import com.project.manager.entities.Project;
import com.project.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepositoryr) {
        this.projectRepository = projectRepositoryr;
    }

    public List<Project> projectsOfUser() {
        return projectRepository.findByMembers_Id(1L);
    }


}
