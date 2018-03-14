package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

public class ProjectViewScene extends CustomSceneImpl {

    public ProjectViewScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Project View");
        super.setPathToFXML("/fxml/projectView.fxml");
    }
}
