package com.project.manager.services;

import com.project.manager.entities.UserModel;
import com.project.manager.exceptions.EmptyUsernameException;
import com.project.manager.exceptions.NotEnoughPermissionsException;
import com.project.manager.exceptions.UserDoesNotExistException;
import com.project.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Patryk Sadok
 * Class responsible for validating logged in user's permissions
 * and returning chosen user with button.
 * <p>
 * Class performs method, which gets list of all usernames in database.
 * Class performs method, which returns UserModel of chosen user.
 */
@Service
public class UserSelectorService {

    private UserRepository userRepository;
    private SessionService sessionService;
    static  String role = "USER";

    @Autowired
    public UserSelectorService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sessionService = SessionService.getInstance();
    }

    /**
     * This method returns list of all usernames in database.
     *
     * @return list of usernames
     */
    public List<String> getUserList() {
        return userRepository.findAllUsernames();
    }

    /**
     * This method returns model of chosen user.
     *
     * @param username given from textfield, parameter for searching in database.
     * @return UserModel of found user
     */
    public UserModel findUser(String username) {

        if (username.isEmpty()) {
            throw new EmptyUsernameException("Username field can't be empty.");
        }
        UserModel usermodel = userRepository.findByUsername(username);

        if (!Optional.ofNullable(userRepository.findByUsername(username)).isPresent()) {
            throw new UserDoesNotExistException("There is no user with that username in our service.");
        }
        role = sessionService.getRole().toString();
        if (role.equals("USER")) {
            throw new NotEnoughPermissionsException("You do not have enough permissions to do that.");
        }

        return usermodel;
    }
}