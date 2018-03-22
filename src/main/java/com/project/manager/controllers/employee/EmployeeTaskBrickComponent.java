package com.project.manager.controllers.employee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Getter
@Setter
public class EmployeeTaskBrickComponent  implements Initializable{
    @FXML
    private Pane priorityColor;

    @FXML
    private Label tagLabel;

    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
