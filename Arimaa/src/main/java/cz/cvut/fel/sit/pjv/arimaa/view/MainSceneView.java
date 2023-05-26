package cz.cvut.fel.sit.pjv.arimaa.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainSceneView {
    Stage mainWindow;
    public MainSceneView(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public final Scene display() {
        Button startGameButton = new Button("Start Game");

        GameView gameView = new GameView(mainWindow);
        startGameButton.setOnAction(e -> mainWindow.setScene(gameView.display()));

        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsView.display());
        Label MenuLabel = new Label("Arimaa Game");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(MenuLabel, startGameButton, gameSettingsButton);

        return new Scene(layout, 500, 500);
    }
}
