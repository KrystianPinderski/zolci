package com.project.manager.data;

import com.project.manager.entities.Task;
import com.project.manager.models.TaskPririty;
import com.project.manager.models.TaskStatus;
import com.project.manager.repositories.TaskRepository;
import com.project.manager.utils.BCryptEncoder;
import com.project.manager.entities.Project;
import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import com.project.manager.repositories.ProjectRepository;
import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;

/**
 * This is the class which is responsible for injecting test data into database.
 * This class perform injection of test data into database and relations between tables
 */
@Component
public class InjectData {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public InjectData(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method perform injection of test data into database and relations between tables
     */
    @PostConstruct
    public void injectData() {

        /*
        Inicialize models
         */
        UserModel user = UserModel.builder()
                .username("user")
                .activationCode(String.valueOf(new Date().getTime()))
                .password(BCryptEncoder.encode("password"))
                .email("user@mail.com")
                .role(UserRole.USER)
                .isFirstLogin(false)
                .projects(new HashSet<>())
                .build();

        UserModel manager = UserModel.builder()
                .username("manager")
                .activationCode(String.valueOf(new Date().getTime()))
                .password(BCryptEncoder.encode("password"))
                .email("manager@mail.com")
                .role(UserRole.USER)
                .isFirstLogin(false)
                .projects(new HashSet<>())
                .build();

        Project projectOne = Project.builder()
                .projectName("Lotnisko")
                .projectInformation("project1Info")
                .members(new HashSet<>())
                .tasks(new HashSet<>())
                .build();

        Project projectTwo = Project.builder()
                .projectName("Przychowywacz haseł")
                .projectInformation("project2Info")
                .members(new HashSet<>())
                .tasks(new HashSet<>())
                .build();

        Project projectThree = Project.builder()
                .projectName("Projekt inżynierski")
                .projectInformation("project3Info")
                .members(new HashSet<>())
                .tasks(new HashSet<>())
                .build();

        Task task1 = Task.builder()
                .name("Stworzenie szkieletu")
                .description("Podstawowy szkielet funkcionalnej aplikacji")
                .taskStatus(TaskStatus.DONE.ordinal())
                .tag("START")
                .priority(TaskPririty.HIGHT.ordinal())
                .build();
        Task task2 = Task.builder()
                .name("Baza danych")
                .description("Dodanie do projektu połaczenia z baza danych")
                .taskStatus(TaskStatus.DONE.ordinal())
                .tag("BD")
                .priority(TaskPririty.HIGHT.ordinal())
                .build();
        Task task3 = Task.builder()
                .name("Entity")
                .description("Stworznie wszystkich wymaganych entity na potrzeby projektu")
                .taskStatus(TaskStatus.CODE_REVIEW.ordinal())
                .tag("BD")
                .priority(TaskPririty.MEDIUM.ordinal())
                .build();
        Task task4 = Task.builder()
                .name("Połaczenie z bazą")
                .description("Stworznie repozytowrów JPA dla entity oraz wykonanie testów zapisu do bazy dancyh")
                .taskStatus(TaskStatus.TESTING.ordinal())
                .tag("JP")
                .priority(TaskPririty.MEDIUM.ordinal())
                .build();
        Task task5 = Task.builder()
                .name("Logika aplikacji")
                .description("stworznie potrzebnej logiki aplikacji do zarazdzania i kontrolą lotów na lotnisku")
                .taskStatus(TaskStatus.TESTING.ordinal())
                .tag("SERVICE")
                .priority(TaskPririty.HIGHT.ordinal())
                .build();
        Task task6 = Task.builder()
                .name("kontrolery")
                .description("Dodanie potrzebnych kontrolerów")
                .taskStatus(TaskStatus.IN_PROGRESS.ordinal())
                .tag("REST")
                .priority(TaskPririty.MEDIUM.ordinal())
                .build();
        Task task7 = Task.builder()
                .name("testownie")
                .description("Testownie integracyjne bazy andych z logiką oraz kontrolerami")
                .taskStatus(TaskStatus.SPRINT_BACKLOG.ordinal())
                .tag("[TEST]")
                .priority(TaskPririty.LOW.ordinal())
                .build();
        Task task8 = Task.builder()
                .name("FrontEnd")
                .description("stwornzie szkieletu fronu aplikacji")
                .taskStatus(TaskStatus.SPRINT_BACKLOG.ordinal())
                .tag("[FE]")
                .priority(TaskPririty.HIGHT.ordinal())
                .build();
        Task task9 = Task.builder()
                .name("Security")
                .description("Zabezpieczenie aplikacji przed działaniami szkodzącymi")
                .taskStatus(TaskStatus.PRODUCT_BACKLOG.ordinal())
                .tag("[SEC]")
                .priority(TaskPririty.LOW.ordinal())
                .build();
        Task task10 = Task.builder()
                .name("Wdrązenie")
                .description("Wdrązenie aplikacji na serwer")
                .taskStatus(TaskStatus.PRODUCT_BACKLOG.ordinal())
                .tag("[END]")
                .priority(TaskPririty.LOW.ordinal())
                .build();

        /*
        Save models
         */
        user = userRepository.save(user);
        manager = userRepository.save(manager);

        projectOne = projectRepository.save(projectOne);
        projectTwo = projectRepository.save(projectTwo);
        projectThree = projectRepository.save(projectThree);

        task1 = taskRepository.save(task1);
        task2 = taskRepository.save(task2);
        task3 = taskRepository.save(task3);
        task4 = taskRepository.save(task4);
        task5 = taskRepository.save(task5);
        task6 = taskRepository.save(task6);
        task7 = taskRepository.save(task7);
        task8 = taskRepository.save(task8);
        task9 = taskRepository.save(task9);
        task10 = taskRepository.save(task10);

        user.getProjects().add(projectOne);
        user.getProjects().add(projectTwo);
        user.getProjects().add(projectThree);

        manager.getProjects().add(projectOne);
        manager.getProjects().add(projectTwo);
        manager.getProjects().add(projectThree);

        projectOne.getMembers().add(user);
        projectTwo.getMembers().add(user);
        projectThree.getMembers().add(user);

        projectOne.setManager(manager);
        projectTwo.setManager(manager);
        projectThree.setManager(manager);

        task1.setProject(projectOne);
        task2.setProject(projectOne);
        task3.setProject(projectOne);
        task4.setProject(projectOne);
        task5.setProject(projectOne);
        task6.setProject(projectOne);
        task7.setProject(projectOne);
        task8.setProject(projectOne);
        task9.setProject(projectOne);
        task10.setProject(projectOne);

        projectOne.getTasks().add(task1);
        projectOne.getTasks().add(task2);
        projectOne.getTasks().add(task3);
        projectOne.getTasks().add(task4);
        projectOne.getTasks().add(task5);
        projectOne.getTasks().add(task6);
        projectOne.getTasks().add(task7);
        projectOne.getTasks().add(task8);
        projectOne.getTasks().add(task9);
        projectOne.getTasks().add(task10);

        userRepository.save(user);
        userRepository.save(manager);
        projectRepository.save(projectOne);
        projectRepository.save(projectTwo);
        projectRepository.save(projectThree);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);
        taskRepository.save(task7);
        taskRepository.save(task8);
        taskRepository.save(task9);
        taskRepository.save(task10);
    }
}
