package com.project.manager.services;

import com.project.manager.entities.UserModel;
import com.project.manager.exceptions.EmptyUsernameException;
import com.project.manager.exceptions.UserDoesNotExistException;
import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddUserService {
    private UserRepository userRepository;
    private SessionService sessionService;

    @Autowired
    public AddUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sessionService = SessionService.getInstance();
    }

    public void findUser(String username) {
        if (username.isEmpty()) {
            throw new EmptyUsernameException("Username field can't be empty.");
        }
        UserModel usermodel = userRepository.findByUsername(username);

        if (!Optional.ofNullable(userRepository.findByUsername(username)).isPresent()) {
            throw new UserDoesNotExistException("There is no user with that username in our service.");
        }
    }
}
