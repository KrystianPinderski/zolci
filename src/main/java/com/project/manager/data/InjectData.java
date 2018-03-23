package com.project.manager.data;

import com.project.manager.BCryptEncoder;
import com.project.manager.entities.Project;
import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import com.project.manager.repositories.ProjectRepository;
import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

/**
 * This is the class which is responsible for injecting test data into database.
 * This class perform injection of test data into database and relations between tables
 */
@Component
public class InjectData {

    private ProjectRepository projectRepository;

    private UserRepository userRepository;

    @Autowired
    public InjectData(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method perform injection of test data into database and relations between tables
     */
//   @PostConstruct
    public void injectData() {
       UserModel userOne = UserModel.builder()
               .username("user")
               .unlockCode(String.valueOf(new Date().getTime()))
               .password(BCryptEncoder.encode("password"))
               .email("user@mail.com")
               .role(UserRole.USER)
               .isLocked(false)
               .projectsAsUser(new HashSet<>())
               .firstName("Adam")
               .lastName("Spadam")
               .build();

       UserModel client = UserModel.builder()
               .username("client")
               .unlockCode(String.valueOf(new Date().getTime()))
               .password(BCryptEncoder.encode("password"))
               .email("client@mail.com")
               .role(UserRole.CLIENT)
               .isLocked(false)
               .projectsAsClient(new HashSet<>())
               .firstName("Benek")
               .lastName("Bebenek")
               .build();

       UserModel manager = UserModel.builder()
               .username("manager")
               .unlockCode(String.valueOf(new Date().getTime()))
               .password(BCryptEncoder.encode("password"))
               .email("manager@mail.com")
               .role(UserRole.USER)
               .isLocked(false)
               .projectsAsClient(new HashSet<>())
               .firstName("Edward")
               .lastName("Oncki")
               .projectsAsManager(new HashSet<>())
               .build();

       Project projectOne = Project.builder()
               .projectName("project1")
               .projectInformation("project1Info")
               .members(new HashSet<>())
               .clients(new HashSet<>())
               .build();

//        Project projectTwo = Project.builder()
//                .projectName("project2")
//                .projectInformation("project2Info")
//                .members(new HashSet<>())
//                .build();
//
//        Project projectThree = Project.builder()
//                .projectName("project3")
//                .projectInformation("project3Info")
//                .members(new HashSet<>())
//                .build();

       userOne = userRepository.save(userOne);
       client = userRepository.save(client);
       manager = userRepository.save(manager);
       projectOne = projectRepository.save(projectOne);
//        projectTwo = projectRepository.save(projectTwo);
//        projectThree = projectRepository.save(projectThree);

       userOne.getProjectsAsUser().add(projectOne);
//        userOne.getProjectsAsUser().add(projectTwo);
//        userOne.getProjectsAsUser().add(projectThree);

       client.getProjectsAsClient().add(projectOne);

       manager.getProjectsAsManager().add(projectOne);

       projectOne.getMembers().add(userOne);
//        projectTwo.getMembers().add(userOne);
//        projectThree.getMembers().add(userOne);

       projectOne.getClients().add(client);

       projectOne.setManager(manager);

       userRepository.save(userOne);
       userRepository.save(client);
       userRepository.save(manager);
       projectRepository.save(projectOne);
//        projectRepository.save(projectTwo);
//        projectRepository.save(projectThree);


        UserModel admin = UserModel
                .builder()
                .username("admin")
                .password(BCryptEncoder.encode("admin"))
                .email("admin@mail.com")
                .role(UserRole.ADMIN)
                .isLocked(false)
                .unlockCode(String.valueOf(new Date().getTime()))
                .build();
        userRepository.save(admin);
    }

}
