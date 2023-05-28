package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainSceneView {
    Stage mainWindow;
    public MainSceneView(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public final Scene display() {
        HBox topMenu = new HBox(); /*TODO change to differentLayout*/
        BorderPane borderPane = new BorderPane();

        /*TOP menu*/
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());
        topMenu.getChildren().addAll(gameSettingsButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        borderPane.setRight(topMenu);
        /*TOP menu*/

        VBox centerMenu = new VBox(10);
        Label MenuLabel = new Label("Arimaa Game");
        centerMenu.setAlignment(Pos.CENTER);

        Button startTestGameButton = new Button("Start test game");
        startTestGameButton.setOnAction(e -> {
            GameView gameView = new GameView(mainWindow, Board.createTestBoard());
            mainWindow.setScene(gameView.display());
        });
        Button startSimpleGameButton = new Button("Start classic game");
        startSimpleGameButton.setOnAction(e -> {
            Board board = Board.createEmptyBoard();
            SetupGameView setupGameView = new SetupGameView(mainWindow, board,
                    board.getGoldenPlayer().getAllAvailablePieces(),
                    board.getSilverPlayer().getAllAvailablePieces());

            mainWindow.setScene(setupGameView.display());
        });
        centerMenu.getChildren().addAll(MenuLabel,startSimpleGameButton, startTestGameButton );
        borderPane.setCenter(centerMenu);

        return new Scene(borderPane, 600, 600);
    }
}
