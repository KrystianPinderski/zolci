package com.project.manager.repositories;

import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    UserModel findByEmail(String email);

    List<UserModel> getAllByRole(UserRole userRole);
}
