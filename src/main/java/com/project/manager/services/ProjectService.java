package com.project.manager.services;

import com.project.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class ProjectService {

    @PersistenceContext
    private EntityManager em;

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepositoryr) {
        this.projectRepository = projectRepositoryr;
    }

    public void projectsOfUser() {
        projectRepository.findByMembers_Id(1L);
        System.out.println("test");
//        Query query = em.createNativeQuery("SELECT projectname FROM project AS pr, user_project AS up WHERE up.user_id = 1 AND pr.id = up.project_id");
//        List result = query.getResultList();
    }


}
