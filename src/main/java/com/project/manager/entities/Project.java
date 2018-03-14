package com.project.manager.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "PROJECT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String projectName;

    @NotNull
    @NotEmpty
    private String projectInformation;

    @ManyToMany(mappedBy = "projects")//, fetch = FetchType.EAGER)
    private Set<UserModel> members = new HashSet<>();
}
