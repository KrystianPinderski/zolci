package com.project.manager.services;

import com.project.manager.entities.Project;
import com.project.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private SessionService sessionService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.sessionService = SessionService.getInstance();
    }

    public List<Project> projectsOfUser() {
        return projectRepository.findByMembers_Id(sessionService.getID());
    }

}
