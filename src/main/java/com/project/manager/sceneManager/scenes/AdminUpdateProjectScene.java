package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

public class AdminUpdateProjectScene extends CustomSceneImpl {
    public AdminUpdateProjectScene(Stage primaryStage) {
            super(primaryStage);
            super.setWindowTitle("Update Window");
            super.setPathToFXML("/fxml/adminUpdateProject.fxml");
    }
}
