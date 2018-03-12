package com.project.manager.entities;

import com.project.manager.models.UserRole;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    private boolean isFirstLogin;

    @NotNull
    private String code;
}

