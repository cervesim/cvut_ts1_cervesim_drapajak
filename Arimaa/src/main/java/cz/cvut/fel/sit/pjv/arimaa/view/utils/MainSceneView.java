package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import javafx.geometry.Insets;
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

    public Scene display() {
        /*TOP menu*/
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());

        VBox topSettingsButton = new VBox(gameSettingsButton); /*TODO change to differentLayout*/
        topSettingsButton.setPadding(new Insets(2, 5, 2, 2));
        topSettingsButton.setAlignment(Pos.TOP_RIGHT);
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
        centerMenu.getChildren().addAll(MenuLabel, startSimpleGameButton, startTestGameButton);
        BorderPane borderPane = new BorderPane(centerMenu, topSettingsButton, null, null, null);

        return new Scene(borderPane, 600, 600);
    }
}
