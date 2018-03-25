package com.project.manager.repositories;

import com.project.manager.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    UserModel findByEmail(String email);

    //@Query(value = "SELECT USER_MODEL.USERNAME FROM USER_MODEL", nativeQuery = true)
    //Optional List<String> findAllUsernames();
}
