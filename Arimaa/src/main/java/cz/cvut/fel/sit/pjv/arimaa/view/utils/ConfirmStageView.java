package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmStageView {
    static boolean answer;
    public static boolean display(String title, String text){
        Stage confirmBox = new Stage();
        confirmBox.initModality(Modality.APPLICATION_MODAL); /*blocks inputs in other windows until this is closed*/
        confirmBox.setTitle(title);
        confirmBox.setWidth(250);
        confirmBox.setHeight(200);

        Label label = new Label();
        label.setText(text);
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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        confirmBox.setScene(scene);
        confirmBox.showAndWait(); /*waits for closing is here because of modality*/

        return answer;
    }
}
