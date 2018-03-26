package com.project.manager.controllers;

import com.project.manager.exceptions.DifferentPasswordException;
import com.project.manager.exceptions.EmptyPasswordException;
import com.project.manager.exceptions.EmptyUsernameException;
import com.project.manager.exceptions.UserDoesNotExistException;
import com.project.manager.models.UserRole;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.services.SessionService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.project.manager.services.LoginService;

/**
 * This is the class which is responsible for login window.
 * This class perform display input fields, error labels and references to other frames.
 */
@Component
public class LoginController implements Initializable {

    @FXML
    private Button register;
    @FXML
    private Button loginButton;
    @FXML
    private Label labelForgotPassword;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPassField;
    @FXML
    private Label labelErrorUsername;
    @FXML
    private Label labelErrorPassword;

    private SceneManager sceneManager;
    private LoginService loginService;
    private SessionService sessionService;

    @Autowired
    public LoginController(LoginService loginService) {
        sceneManager = SceneManager.getInstance();
        this.loginService=loginService;
        this.sessionService = SessionService.getInstance();
    }

    /**
     * Initialization of login frame
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resetUsernameError();
        this.resetPasswordError();


        register.setOnAction(e -> {
            sceneManager.showScene(SceneType.REGISTRATION);
        });

        loginButton.setOnAction(e -> {
            this.resetUsernameError();
            this.resetPasswordError();
            try {
                String username = usernameTextField.getText().toString();
                String passedPassword = passwordPassField.getText().toString();
                loginService.loginUser(username, passedPassword);
                UserRole role = sessionService.getRole();
                switch (role) {
                    case USER:
                        sceneManager.showScene(SceneType.DASHBOARD);
                        break;
                    case ADMIN:
                        sceneManager.showScene(SceneType.ADMIN_DASHBOARD);
                }

            }
            catch (DifferentPasswordException dpe) {
                labelErrorPassword.setText(dpe.getMessage());
            }
            catch (UserDoesNotExistException udnee) {
                labelErrorUsername.setVisible(true);
                labelErrorUsername.setText(udnee.getMessage());
            } catch (EmptyUsernameException eue) {
                labelErrorUsername.setVisible(true);
                labelErrorUsername.setText(eue.getMessage());
            } catch (EmptyPasswordException epe){
                labelErrorUsername.setVisible(true);
                labelErrorPassword.setText(epe.getMessage());
            }
        });

        labelForgotPassword.setOnMouseClicked(e -> {
            //sceneManager.showScene(SceneType.FORGOTPASSWD);
        });
    }

    /**
     * Method responsible for resetting username's error label.
     */
    public void resetUsernameError() {
        labelErrorUsername.setText("");
        labelErrorUsername.setVisible(false);
    }

    /**
     * Method responsible for resetting password's error label.
     */
    public void resetPasswordError() {
        labelErrorPassword.setText("");
        labelErrorPassword.setVisible(false);
    }
}
