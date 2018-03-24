package com.project.manager.sceneManager.scenes;

import com.project.manager.sceneManager.scenes.system.CustomSceneImpl;
import javafx.stage.Stage;

public class MessageViewWindowScene extends CustomSceneImpl {

    public MessageViewWindowScene(Stage stage) {
        super(stage);
        super.setWindowTitle("Message");
        super.setPathToFXML("/fxml/messageViewWindow.fxml");
    }
}
