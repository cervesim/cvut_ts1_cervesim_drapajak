package cz.cvut.fel.sit.pjv.arimaa;

import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArimaaGame_startMenu extends Application {
    private static final Logger logger = Logger.getLogger(ArimaaGame_startMenu.class.getName());
    private Stage mainWindow;

    @Override
    public void start(Stage mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.mainWindow.setTitle("Arimaa_cervesim");
        configureLogger();

        mainWindow.setOnCloseRequest(e -> {
            closeAndSave();
            e.consume();
        });
        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        this.mainWindow.setScene(mainSceneView.display());
        this.mainWindow.show();

    }

    private void configureLogger() throws IOException {
        FileHandler fileHandler = new FileHandler("application.log", true);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);

        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
    }

    public void closeAndSave() {
        if (ConfirmBoxView.display("Confirm exit", "Are you sure you want to exit?")) {
            mainWindow.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
