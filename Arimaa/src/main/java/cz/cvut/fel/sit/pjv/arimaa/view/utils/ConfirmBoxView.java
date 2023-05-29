package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene. layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBoxView {
    public static boolean answer;
    public static Boolean display(String title, String text){
        Stage confirmBox = new Stage();
        confirmBox.initModality(Modality.APPLICATION_MODAL); /*blocks inputs in other windows until this is closed*/
        confirmBox.setTitle(title);
        confirmBox.setWidth(300);
        confirmBox.setHeight(200);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            confirmBox.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            confirmBox.close();
        });

        Label label = new Label(text);
        VBox labelLayout = new VBox(2, label);
        labelLayout.setAlignment(Pos.CENTER);


        HBox hbox = new HBox(5, yesButton, noButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0, 0, 30, 0));
        BorderPane borderPane = new BorderPane(labelLayout, null, null, hbox, null);

        Scene scene = new Scene(borderPane);
        confirmBox.setScene(scene);
        confirmBox.showAndWait(); /*waits for closing is here because of modality*/

        return answer;
    }
}
