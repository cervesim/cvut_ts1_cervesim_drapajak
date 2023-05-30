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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        Label MenuLabel = new Label("Arimaa Game"); /*Label*/
        /*NormalGameButton*/
        Button startSimpleGameButton = new Button("Start classic game");
        startSimpleGameButton.setOnAction(e -> {
            SetupGameView setupGameView = new SetupGameView(mainWindow);
            mainWindow.setScene(setupGameView.display());
        });
        /*NormalGameButton*/
        /*GameAgainstComputer*/
        Button startGameAgainstComputer = new Button("Start game against computer");
        startGameAgainstComputer.setOnAction(e -> {
            Board board = Board.createBoardWithSilverPiecesSet();
            SetupGameView setupGameWithBotView = new SetupGameView(mainWindow, board,
                    board.getGoldenPlayer().getAllAvailablePieces(),
                    new ArrayList<>());

            mainWindow.setScene(setupGameWithBotView.display());
        });
        /*GameAgainstComputer*/
        /*TestGameButton*/
        Button startTestGameButton = new Button("Start test game");
        startTestGameButton.setOnAction(e -> {
            GameView gameView = new GameView(mainWindow);
            mainWindow.setScene(gameView.display());
        });
        /*TestGameButton*/

        VBox centerMenu = new VBox(10, MenuLabel, startSimpleGameButton, startGameAgainstComputer, startTestGameButton);
        centerMenu.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane(centerMenu, topSettingsButton, null, null, null);
        return new Scene(borderPane, 600, 600);
    }
}
