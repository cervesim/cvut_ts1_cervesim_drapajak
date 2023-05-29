package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.GameEndedStageView;
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
        MainSceneView mainSceneView = new MainSceneView(mainWindow);

        /*TOP menu*/
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->{
            boolean answer = ConfirmBoxView.display("Exit game", "Are you sure you want to exit game?");
            if(answer) mainWindow.setScene(mainSceneView.display());
        });

        if (board.gameEnded){
            String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
            boolean answer = ConfirmBoxView.display("Game ended", text);
            if (answer) mainWindow.setScene(mainSceneView.display());
        }

        HBox topMenu = new HBox(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        /*TOP menu*/

        /*BoardView + GameView*/
        BoardView boardView = new BoardView(board);
        BorderPane borderPane = new BorderPane(boardView.display(), topMenu, null, null, null);
        /*BoardView + GameView*/

        return new Scene(borderPane, 500, 500);
    }

}
