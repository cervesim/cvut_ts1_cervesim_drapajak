package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class GameUtils {
    public static void setBoardGridPane(GridPane gridPane){
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setAlignment(Pos.CENTER);
    }
}

