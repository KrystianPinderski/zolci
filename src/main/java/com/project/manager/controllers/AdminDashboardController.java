package com.project.manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdminDashboardController implements Initializable {
    @FXML
    private Tab projectsTab;

    @FXML
    private Tab usersTab;

    @FXML
    private Tab inboxTab;

    @FXML
    private TableView<?> projectTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
