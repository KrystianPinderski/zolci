package com.project.manager.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphicButtonGenerator {

    public JFXButton getJfxButtonWithGraphic(String path) {
        JFXButton del = new JFXButton();
        Image image = new Image(getClass().getResourceAsStream(path));
        del.setGraphic(new ImageView(image));
        return del;
    }
}
