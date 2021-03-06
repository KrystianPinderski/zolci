package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

/**
 * Dashboard Scene, view list of projects logged user
 */
public class DashboardScene extends CustomSceneImpl {

    public DashboardScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Dashboard");
        super.setPathToFXML("/fxml/dashboard.fxml");
    }
}
