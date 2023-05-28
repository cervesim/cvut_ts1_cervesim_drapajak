package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmStageView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView {
    static Stage mainWindow;
    Board board;
    public GameView(Stage mainWindow, Board board) {
        this.mainWindow = mainWindow;
        this.board = board;
    }

    public final Scene display(){
        BorderPane borderPane = new BorderPane();
        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        /*TOP menu TODO add to class or something*/
        HBox topMenu = new HBox();
        Button gameSettingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->{
            mainWindow.setScene(mainSceneView.display());

        });
        if (board.gameEnded){
            String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
            ConfirmStageView.display("Game end", text);
        }
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());
        topMenu.getChildren().addAll(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        borderPane.setTop(topMenu);
        /*TOP menu*/
        /*BoardView*/
        Label boardLabel = new Label("Welcome in game");

        VBox boardLayout = new VBox(10);
        boardLayout.setAlignment(Pos.CENTER);
        boardLayout.getChildren().add(boardLabel);

        BoardView boardView = new BoardView(board);
        borderPane.setCenter(boardView.display());

        /*BoardView*/
        return new Scene(borderPane, 500, 500);
    }

}
