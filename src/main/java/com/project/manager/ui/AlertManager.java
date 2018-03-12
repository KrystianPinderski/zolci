package com.project.manager.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertManager {

    public static void showInformationAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showErrorAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<ButtonType> showConfirmationAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setHeaderText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
}
