package com.project.manager.controllers;

import com.project.manager.exceptions.EmptyUsernameException;
import com.project.manager.exceptions.UserDoesNotExistException;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import com.project.manager.services.AddUserService;
import com.project.manager.ui.components.ProjectPaneGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AddUserController implements Initializable {

    private SceneManager sceneManager;

    private ProjectPaneGenerator projectPaneGenerator;
    private AddUserService addUserService;

    @FXML
    private Label errorLabel;
    @FXML
    private Button addUserButton;
    @FXML
    private ListView<String> usersList;
    @FXML
    private TextField usernameTextField;

    @Autowired
    public AddUserController(AddUserService addUserService) {
        sceneManager = SceneManager.getInstance();
        this.addUserService=addUserService;
    }

    /**
     * Initialization of AddUser view with list of users
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.reserLabelError();

        addUserButton.setOnMouseClicked(e -> {
            this.reserLabelError();
            try {
                String username = usernameTextField.getText().toString();
                addUserService.findUser(username);
            } catch (UserDoesNotExistException udnee) {
                errorLabel.setVisible(true);
                errorLabel.setText(udnee.getMessage());
            } catch (EmptyUsernameException eue) {
                errorLabel.setVisible(true);
                errorLabel.setText(eue.getMessage());
            }
        });
    }

    public void reserLabelError() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }
}
