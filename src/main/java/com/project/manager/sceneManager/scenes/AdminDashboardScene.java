package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

/**
 * Scene of admin with his dashboard containing all admin functionality
 */
public class AdminDashboardScene extends CustomSceneImpl {

    public AdminDashboardScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Admin Dashboard");
        super.setPathToFXML("/fxml/adminDashboard.fxml");
    }
}