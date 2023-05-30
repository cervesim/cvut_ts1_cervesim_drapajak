package cz.cvut.fel.sit.pjv.arimaa.view.setupGameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

public class SetupGameView {
    protected Stage mainWindow;
    Board board;
    Collection<Piece> goldenPlayerPieces;
    Collection<Piece> silverPlayerPieces;
    public boolean boardIsSet;
    public static Piece pieceToSet;

    public SetupGameView(Stage mainWindow, Board board, Collection<Piece> goldenPlayerPieces, Collection<Piece> silverPlayerPieces) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.goldenPlayerPieces = goldenPlayerPieces;
        this.silverPlayerPieces = silverPlayerPieces;
        this.boardIsSet = (goldenPlayerPieces.isEmpty() && silverPlayerPieces.isEmpty());
    }
    public SetupGameView(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.board = Board.createEmptyBoard();
        this.goldenPlayerPieces = board.getGoldenPlayer().getAllAvailablePieces();
        this.silverPlayerPieces = board.getSilverPlayer().getAllAvailablePieces();
        this.boardIsSet = false;
    }


    public Scene display() {
        HBox topMenu = makeTopMenu();/*Top menu*/

        VBox botMenu = makeBottomMenu();/*Bottom menu*/

        /*Right menu*/
        VBox rightVbox = makeLeftAndRightMenu(goldenPlayerPieces, "Golden player pieces");
        /*Right menu*/

        VBox leftVbox = makeLeftAndRightMenu(silverPlayerPieces, "Silver player pieces"); /*Left menu*/

        SetupBoardView setupBoardView = new SetupBoardView(mainWindow, board, goldenPlayerPieces, silverPlayerPieces);
        BorderPane borderPane = new BorderPane(setupBoardView.display(), topMenu, rightVbox, botMenu, leftVbox);

        return setScene(borderPane);
    }
    private VBox makeLeftAndRightMenu(Collection<Piece> pieces, String whichPieces) {
        ChoiceBox<Piece> pieceChoiceBox = new ChoiceBox<>();
        pieceChoiceBox.getItems().addAll(pieces);
        pieceChoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> pieceToSet = newValue);
        Label label = new Label(whichPieces);

        VBox vBox = new VBox(label, pieceChoiceBox);
        if (whichPieces.equals("Golden player pieces")){
            vBox.setAlignment(Pos.TOP_RIGHT);
        }
        return vBox;
    }
    private VBox makeBottomMenu(){
        Button startGameButton = new Button("StartGame");
        startGameButton.setOnAction(e -> {
            if (boardIsSet){
                GameView gameView = new GameView(mainWindow, board);
                mainWindow.setScene(gameView.display());
            }
        });
        VBox botMenu = new VBox(startGameButton);
        botMenu.setAlignment(Pos.CENTER);
        return botMenu;
    }
    protected HBox makeTopMenu(){
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());

        Button testButton = new Button();
        testButton.setOnAction(e -> boardIsSet = true);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->{
            boolean answer = ConfirmBoxView.display("Exit game", "Are you sure you want to exit game?");
            MainSceneView mainSceneView = new MainSceneView(mainWindow);
            if(answer) mainWindow.setScene(mainSceneView.display());
        });
        HBox topMenu = new HBox(gameSettingsButton, exitButton, testButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        return topMenu;
    }
    protected Scene setScene(BorderPane borderPane){
        return new Scene(borderPane, 700, 600);
    }
}
