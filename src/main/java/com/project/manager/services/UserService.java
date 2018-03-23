package com.project.manager.services;

import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.getAllByRole(UserRole.USER);
    }

    public void delete(long l) {
        userRepository.deleteById(l);
    }

    public void changeLockStatus(boolean b, Long userId) {
        UserModel user = userRepository.getOne(userId);
        user.setLocked(!b);
        userRepository.save(user);
    }

    public void changePassword(long l) {
        System.out.println(l);
    }
}
