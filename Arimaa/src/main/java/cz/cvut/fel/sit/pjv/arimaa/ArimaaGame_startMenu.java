package cz.cvut.fel.sit.pjv.arimaa;

import cz.cvut.fel.sit.pjv.arimaa.view.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ArimaaGame_startMenu extends Application {
    /**
     * @param stage
     * @throws IOException
     */
    Stage mainWindow;
    @Override
    public void start(Stage mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.mainWindow.setTitle("Arimaa_cervesim");

        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        this.mainWindow.setScene(mainSceneView.display());
        this.mainWindow.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }


}