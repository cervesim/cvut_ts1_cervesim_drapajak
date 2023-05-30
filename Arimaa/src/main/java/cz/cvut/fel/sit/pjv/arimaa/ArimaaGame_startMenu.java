package cz.cvut.fel.sit.pjv.arimaa;

import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ArimaaGame_startMenu extends Application {
    Stage mainWindow;
    @Override
    public void start(Stage mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.mainWindow.setTitle("Arimaa_cervesim");

//        mainWindow.setOnCloseRequest(e -> {
//            closeAndSave();
//            e.consume();
//        }); /*TODO uncomment*/

        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        this.mainWindow.setScene(mainSceneView.display());
        this.mainWindow.show();
    }
    public void closeAndSave(){
        if (ConfirmBoxView.display("Confirm exit", "Are you sure you want to exit?")){
            System.out.println("file is saved"); /*TODO save game*/
            mainWindow.close();
        }
    }
    public static void main(String[] args) {
        launch();
    }


}