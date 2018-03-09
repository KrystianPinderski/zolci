package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

public class RegistrationScene extends CustomSceneImpl {

    public RegistrationScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Sign up");
        super.setWidth(600);
        super.setHeight(600);
        super.setPathToFXML("/fxml/registration.fxml");
    }
}