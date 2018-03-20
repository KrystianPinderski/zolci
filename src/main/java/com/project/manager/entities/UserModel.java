package com.project.manager.entities;

import com.project.manager.models.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.core.annotation.AliasFor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_MODEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String username;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @Min(value = 8)
    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean locked;

    @NotNull
    private String unlockCode;

    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @OneToMany(mappedBy = "manager")
    private Set<Project> projectsAsManager;

    @ManyToMany
    @JoinTable(
            name = "CLIENT_PROJECT",
            joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projectsAsClient;

    @ManyToMany
    @JoinTable(
        name = "USER_PROJECT",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projectsAsUser;
}

