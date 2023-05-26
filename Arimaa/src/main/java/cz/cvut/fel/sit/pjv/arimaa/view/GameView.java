package cz.cvut.fel.sit.pjv.arimaa.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView {
    Stage mainWindow;
    public GameView(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public final Scene display(){
        Label boardLabel = new Label("Welcome in game");

        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> mainWindow.setScene(mainSceneView.display()));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(boardLabel, exitButton);

        return new Scene(layout, 500, 500);
    }

}
