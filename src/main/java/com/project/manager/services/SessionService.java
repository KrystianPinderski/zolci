package com.project.manager.services;

import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import org.springframework.stereotype.Service;

/**
 * @author Patryk Sadok
 * Singleton class responsible for holding data about logged in user.
 *
 * Class performs method to set current logged in user.
 * Class perfomrs methods to get logged in user's data.
 */
@Service
public class SessionService {

    private static SessionService instance = null;
    private UserModel userModel;

    public static SessionService getInstance(){
        if(instance == null){
            instance = new SessionService();
        }
        return instance;
    }

    /**
     * Method responsible for setting current logged in user's UserModel.
     * @param userModel model given by LoginService class.
     */
    public void setLoggedUser(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * Method responsible for returning logged in user's email.
     * @return user's email.
     */
    public String getEmail() {
        return userModel.getEmail();
    }

    /**
     * Method responsible for returning logged in user's username.
     * @return user's username.
     */
    public String getUsername() {
        return userModel.getUsername();
    }

    /**
     * Method responsible for returning logged in user's role.
     * @return user's role.
     */
    public UserRole getRole() {
        return userModel.getRole();
    }

    /**
     * Method responsible for returning logged in user's ID.
     * @return user's ID.
     */
    public long getID() {
        return userModel.getId();
    }
}
