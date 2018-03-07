package com.project.manager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "USER_MODEL")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Email
    private String email;

    @Min(value = 8)
    private String password;

    public UserModel(String username, String email, @Min(value = 8) String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
