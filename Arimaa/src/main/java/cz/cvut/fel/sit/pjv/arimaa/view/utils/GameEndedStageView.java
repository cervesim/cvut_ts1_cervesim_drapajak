package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameEndedStageView {
    public static void display(String title, String text, Stage mainWindow){
        Stage confirmBox = new Stage();
        confirmBox.initModality(Modality.APPLICATION_MODAL); /*blocks inputs in other windows until this is closed*/
        confirmBox.setTitle(title);
        confirmBox.setWidth(400);
        confirmBox.setHeight(250);

        Label label = new Label();
        label.setText(text);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            Board newBoard = Board.createEmptyBoard();
            SetupGameView setupGameView = new SetupGameView(mainWindow ,newBoard,
                    newBoard.getGoldenPlayer().getAllAvailablePieces(),
                    newBoard.getSilverPlayer().getAllAvailablePieces());
            confirmBox.close();
        });

        noButton.setOnAction(e -> {
            MainSceneView mainSceneView = new MainSceneView(mainWindow);;
            confirmBox.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        confirmBox.setScene(scene);
        confirmBox.showAndWait(); /*waits for closing is here because of modality*/
    }
}
