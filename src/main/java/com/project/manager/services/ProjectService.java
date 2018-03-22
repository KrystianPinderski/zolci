package com.project.manager.services;

import com.project.manager.entities.Project;
import com.project.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is the class which is responsible for returning list of projects logged user.
 * This class perform method projectsOfUser which returning list of projects by logged user id
 */
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private SessionService sessionService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.sessionService = SessionService.getInstance();
    }

    /**
     * This method perform returning list of projects by logged user id
     * @return list of projects of logged user by id
     */
    public List<Project> projectsOfUser() {
        return projectRepository.findByMembers_Id(sessionService.getID());
    }
}
