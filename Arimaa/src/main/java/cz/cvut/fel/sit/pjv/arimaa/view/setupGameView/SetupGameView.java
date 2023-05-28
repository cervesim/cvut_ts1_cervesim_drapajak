package cz.cvut.fel.sit.pjv.arimaa.view.setupGameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

public class SetupGameView {
    static Stage mainWindow;
    Board board;
    Collection<Piece> goldenPlayerPieces;
    Collection<Piece> silverPlayerPieces;
    boolean boardIsSet;

    public SetupGameView(Stage mainWindow, Board board, Collection<Piece> goldenPlayerPieces, Collection<Piece> silverPlayerPieces) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.goldenPlayerPieces = goldenPlayerPieces;
        this.silverPlayerPieces = silverPlayerPieces;
        this.boardIsSet = (goldenPlayerPieces.isEmpty() && silverPlayerPieces.isEmpty());
    }

    public final Scene display() {
        BorderPane borderPane = new BorderPane();
        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        /*TOP menu TODO add to class or something*/
        HBox topMenu = new HBox();
        Button gameSettingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            mainWindow.setScene(mainSceneView.display());
        });
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());
        topMenu.getChildren().addAll(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        borderPane.setTop(topMenu);
        /*TOP menu*/
        /*Bottom menu*/
        VBox botMenu = new VBox();
        Button startGameButton = new Button("StartGame");
        startGameButton.setOnAction(e -> {
            if (boardIsSet){
                GameView gameView = new GameView(mainWindow, board);
                mainWindow.setScene(gameView.display());
            }
        });
        botMenu.getChildren().add(startGameButton);
        botMenu.setAlignment(Pos.CENTER);
        borderPane.setBottom(botMenu);
        /*Bottom menu*/

        /*BoardView*/
        SetupBoardView setupBoardView = new SetupBoardView(board, goldenPlayerPieces, silverPlayerPieces);
        borderPane.setCenter(setupBoardView.display());
        borderPane.setLeft(makeHbox(goldenPlayerPieces));
        borderPane.setRight(makeHbox(silverPlayerPieces));

        /*BoardView*/
        return new Scene(borderPane, 600, 600);
    }

    private HBox makeHbox(Collection<Piece> pieces) {
        HBox hBox = new HBox();
        ChoiceBox<Piece> pieceChoiceBox = new ChoiceBox<>();
        pieceChoiceBox.getItems().addAll(pieces);
        pieceChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println(newValue); /*TODO deleteAfterFinishing*/
        });

        hBox.getChildren().add(pieceChoiceBox);
        return hBox;
    }


}
