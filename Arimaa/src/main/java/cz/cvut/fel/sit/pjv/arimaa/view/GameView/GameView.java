package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameView{
    protected Stage mainWindow;
    protected Board board;
    protected GameView nextBoard;
    protected GameView currentBoard;
    public GameView previousBoard;
    public boolean gameEnded;
    public GameView(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.board = Board.createTestBoard();
        this.currentBoard = this;
        this.nextBoard = null;
        this.previousBoard = null;
        this.gameEnded = false;
        mainWindow.setUserData(this);
    }
    public GameView(Stage mainWindow, Board board) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.gameEnded = board.gameEnded;
    }
    public Scene display(){
        if (this.gameEnded){
            String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
            boolean answer = ConfirmBoxView.display("Game ended", text);
            System.out.println(board.getInitialSetup().toString());/*TODO destroy it*/
            System.out.println(board.getMovesHistory().toString());/*TODO destroy it*/
            if (answer) return new SetupGameView(mainWindow).display();
        }

        /*BoardView + GameView*/
        BoardView boardView = new BoardView(mainWindow, board);
        BorderPane borderPane = new BorderPane(boardView.displayBoard(),
                makeTopMenu(),
                makeLeftAndRightMenu("Golden player"),
                makeBottomMenu(),
                makeLeftAndRightMenu("Silver player"));
        /*BoardView + GameView*/

        return new Scene(borderPane, 720, 600);
    }
    private VBox makeLeftAndRightMenu(String player) {
        Label label = new Label(player);
        label.setPadding(new Insets(20));
        /*TODO add timer*/
        /*TODO Show notation*/
        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.TOP_CENTER);
        if (player.equals("Golden player")){
            vBox.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY,
                    Insets.EMPTY)));
        } else {
            vBox.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY,
                    Insets.EMPTY)));
        }
        return vBox;
    }
    private HBox makeBottomMenu(){
        Button previousMove = new Button("Previous move");
        previousMove.setOnAction(e -> {
            /*TODO*/
            if (!(previousBoard == null)) {
                mainWindow.setScene(previousBoard.display());
            }
            });
        Button nextMove = new Button("Next move");
        nextMove.setOnAction(e -> {
            /*TODO*/
            if (!(nextBoard == null)) {
                mainWindow.setScene(nextBoard.display());
            }
        });
        Button undoMove = new Button("Undo move");
        undoMove.setOnAction(e -> {
            /*TODO*/
            if (!(gameEnded || previousBoard == null)) {
                previousBoard.nextBoard = null;
                mainWindow.setScene(previousBoard.display());
            }
        });
        HBox botMenu = new HBox(previousMove, undoMove, nextMove);
        botMenu.setAlignment(Pos.CENTER);
        botMenu.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        return botMenu;
    }
    protected HBox makeTopMenu(){
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->{
            boolean answer = ConfirmBoxView.display("Exit game", "Are you sure you want to exit game?");
            MainSceneView mainSceneView = new MainSceneView(mainWindow);
            if(answer) mainWindow.setScene(mainSceneView.display());
        });
        HBox topMenu = new HBox(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(4));
        return topMenu;
    }
    public GameView getCurrentBoard() {
        return currentBoard;
    }

}
