package com.project.manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable{

    @FXML
    private Button register;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     register.setOnAction(e -> {
         System.out.println("Yo!");
     });
    }
}
