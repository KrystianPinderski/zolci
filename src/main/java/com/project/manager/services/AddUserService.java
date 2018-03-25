package com.project.manager.services;

import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    private UserRepository userRepository;
    private SessionService sessionService;

    @Autowired
    public AddUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sessionService = SessionService.getInstance();
    }
}
