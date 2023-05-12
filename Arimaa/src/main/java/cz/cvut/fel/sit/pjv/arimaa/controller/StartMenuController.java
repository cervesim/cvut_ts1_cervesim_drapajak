package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Board board = Board.createTestBoard();
        welcomeText.setText(board.toString());
    }
}