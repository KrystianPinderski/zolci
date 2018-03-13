package com.project.manager.services;

import com.project.manager.entities.UserModel;
import com.project.manager.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private UserModel userModel;
    @Autowired
    public SessionService() {

    }

    public void getData(UserModel userModel) {
        this.userModel=userModel;
        System.out.println(userModel.getEmail());
    }

    public String getEmail() {
        return userModel.getEmail();
    }

    public String getUsername() {
        return userModel.getUsername();
    }

    public UserRole getRole() {
        return userModel.getRole();
    }

    public long getID() {
        return userModel.getId();
    }
}
