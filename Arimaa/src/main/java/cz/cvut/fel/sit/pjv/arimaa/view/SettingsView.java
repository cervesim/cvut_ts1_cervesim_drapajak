package cz.cvut.fel.sit.pjv.arimaa.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsView {

    public static void display(){
        Stage settingsWindow = new Stage();

        settingsWindow.initModality(Modality.APPLICATION_MODAL); /*blocks inputs in other windows until this is closed*/
        settingsWindow.setTitle("Settings");
        settingsWindow.setWidth(250);

        Label label = new Label();
        label.setText("Settings");
        Button exitButton = new Button("save and exit"); /*TODO save settings*/
        exitButton.setOnAction(e -> settingsWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        settingsWindow.setScene(scene);
        settingsWindow.showAndWait(); /*waits for closing is here because of modality*/
    }

}
