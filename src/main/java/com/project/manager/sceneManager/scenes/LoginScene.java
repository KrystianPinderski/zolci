package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

/**
 * Login Scene, first view on application start
 */
public class LoginScene extends CustomSceneImpl {

    public LoginScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Sign in");
        super.setPathToFXML("/fxml/login.fxml");
    }
}
