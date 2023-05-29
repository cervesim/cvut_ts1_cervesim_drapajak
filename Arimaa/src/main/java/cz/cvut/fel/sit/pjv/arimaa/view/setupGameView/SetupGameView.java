package cz.cvut.fel.sit.pjv.arimaa.view.setupGameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

public class SetupGameView {
    Stage mainWindow;
    Board board;
    Collection<Piece> goldenPlayerPieces;
    Collection<Piece> silverPlayerPieces;
    boolean boardIsSet;
    public static Piece pieceToSet;

    public SetupGameView(Stage mainWindow, Board board, Collection<Piece> goldenPlayerPieces, Collection<Piece> silverPlayerPieces) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.goldenPlayerPieces = goldenPlayerPieces;
        this.silverPlayerPieces = silverPlayerPieces;
        this.boardIsSet = (goldenPlayerPieces.isEmpty() && silverPlayerPieces.isEmpty()); /*TODO change back*/
    }

    public final Scene display() {
        MainSceneView mainSceneView = new MainSceneView(mainWindow);
        /*TOP menu*/
        HBox topMenu = new HBox();
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->{
            boolean answer = ConfirmBoxView.display("Exit game", "Are you sure you want to exit game?");
            if(answer) mainWindow.setScene(mainSceneView.display());
        });
        topMenu.getChildren().addAll(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        /*TOP menu*/
        /*Bottom menu*/
        Button startGameButton = new Button("StartGame");
        startGameButton.setOnAction(e -> {
            if (boardIsSet){
                GameView gameView = new GameView(mainWindow, board);
                mainWindow.setScene(gameView.display());
            }
        });
        VBox botMenu = new VBox(startGameButton);
        botMenu.setAlignment(Pos.CENTER);
        /*Bottom menu*/

        /*Right menu*/
        VBox rightVbox = makeVbox(goldenPlayerPieces, "Golden player pieces");
        rightVbox.setAlignment(Pos.TOP_RIGHT);
        /*Right menu*/
        VBox leftVbox = makeVbox(silverPlayerPieces, "Silver player pieces"); /*Left menu*/


        SetupBoardView setupBoardView = new SetupBoardView(mainWindow, board, goldenPlayerPieces, silverPlayerPieces);
        BorderPane borderPane = new BorderPane(setupBoardView.display(), topMenu, rightVbox, botMenu, leftVbox);
        return new Scene(borderPane, 700, 600);
    }

    private VBox makeVbox(Collection<Piece> pieces, String whichPieces) {
        VBox vBox = new VBox();
        ChoiceBox<Piece> pieceChoiceBox = new ChoiceBox<>();
        pieceChoiceBox.getItems().addAll(pieces);
        pieceChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            pieceToSet = newValue;
        });
        Label label = new Label(whichPieces);


        vBox.getChildren().addAll(label, pieceChoiceBox);
        return vBox;
    }
}
