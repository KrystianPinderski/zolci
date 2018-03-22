package com.project.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.project.manager.ui.sceneManager.SceneManager;
import com.project.manager.ui.sceneManager.SceneType;
import com.project.manager.services.RegistrationService;
import com.project.manager.ui.AlertManager;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the class which is responsible for registration window.
 * This class perform registration by using a "Sign up" button if our information about account
 * in the text field are correct
 */
@Component
public class RegistrationController implements Initializable{

    private RegistrationService registrationService;
    private SceneManager sceneManager;

    /**
     * This is the constructor of controller with contain reference to the sceneManager for switching scenes
     * @param registrationService this is reference to registration service with contains all logical
     *                            methods for registration process
     */
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
        sceneManager = SceneManager.getInstance();
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
    private JFXButton cancel;

    @FXML
    private Label problem;

    /**
     * This method is responsible for listening the controller in window, and making action
     * implemented in lambdas expression
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sign.disableProperty().bind(Bindings.isEmpty(username.textProperty())
                .or(Bindings.length(password.textProperty()).lessThan(8))
                .or(Bindings.length(repeatPassword.textProperty()).lessThan(8))
                .or(Bindings.createBooleanBinding(() -> !registrationService.isValidEmailAddress(email.getText()),email.textProperty())));

        sign.setOnAction((e) -> {
            try {
                registrationService.registerUser(username.getText(), email.getText(), password.getText(), repeatPassword.getText());
                AlertManager.showInformationAlert("Registration", "Successful registration, check your email to get login activationCode!");
                sceneManager.showScene(SceneType.LOGIN);
            }
            catch (RuntimeException ex) {
                problem.setStyle("-fx-background-color: #b73634");
                problem.setVisible(true);
                problem.setText(ex.getMessage());
            }
        });

        cancel.setOnAction(e -> {
            sceneManager.showScene(SceneType.LOGIN);
        });


    }
}
