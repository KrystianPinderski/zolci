package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.services.RegistrationService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RegistrationController implements Initializable{

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField repeatPassword;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton sign;

    @FXML
    private Label problem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sign.disableProperty().bind(Bindings.isEmpty(username.textProperty())
                .or(Bindings.length(password.textProperty()).lessThan(8))
                .or(Bindings.length(repeatPassword.textProperty()).lessThan(8))
                .or(Bindings.createBooleanBinding(() -> !registrationService.isValidEmailAddress(email.getText()),email.textProperty())));

        sign.setOnAction((e) -> {
            try {
                registrationService.registerUser(username.getText(), email.getText(), password.getText(), repeatPassword.getText());
                problem.setVisible(true);
                problem.setStyle("-fx-background-color: #65b718");
                problem.setText("Successfully registration, to log in check you email and copy sended code");

            }
            catch (RuntimeException ex) {
                problem.setStyle("-fx-background-color: #b73634");
                problem.setVisible(true);
                problem.setText(ex.getMessage());
            }
        });



    }
}
