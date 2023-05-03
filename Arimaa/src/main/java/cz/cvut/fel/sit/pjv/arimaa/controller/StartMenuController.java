package cz.cvut.fel.sit.pjv.arimaa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Offline game started");
    }
}