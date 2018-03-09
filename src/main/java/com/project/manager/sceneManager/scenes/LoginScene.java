package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

public class LoginScene extends CustomSceneImpl {

    public LoginScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Sign in");
        super.setWidth(300);
        super.setHeight(275);
        super.setPathToFXML("/fxml/login.fxml");
    }
}
