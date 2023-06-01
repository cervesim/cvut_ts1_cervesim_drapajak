package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class GameView{
    protected Stage mainWindow;

    protected Board board;
    protected GameView nextBoard;
    protected GameView currentBoard;
    public GameView previousBoard;
    protected int goldenPlayerTime;
    protected int silverPlayerTime;
    public boolean gameEnded;
    public GameView(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.board = Board.createTestBoard();
        this.currentBoard = this;
        this.nextBoard = null;
        this.previousBoard = null;
        this.gameEnded = false;
        this.goldenPlayerTime = 0;
        this.silverPlayerTime = 0;
        mainWindow.setUserData(this);
    }
    public GameView(Stage mainWindow, Board board) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.currentBoard = this;
        this.nextBoard = null;
        this.previousBoard = null;
        this.gameEnded = board.gameEnded;
        mainWindow.setUserData(this);
    }
    public GameView(Stage mainWindow, Board board, int goldenPlayerTime, int silverPlayerTime) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.gameEnded = board.gameEnded;
        this.goldenPlayerTime = goldenPlayerTime;
        this.silverPlayerTime = silverPlayerTime;
    }
    public Scene display() {
        if (this.gameEnded){
            String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
            boolean answer = ConfirmBoxView.display("Game ended", text);

            System.out.println(board.getInitialSetup().toString());/*TODO destroy it*/
            System.out.println(board.getMovesHistory().toString());/*TODO destroy it*/

            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            Date date = new Date();

            File file = new File("src/main/ArimaaGameHistory/" + formatter.format(date) + ".txt");
            try {
                file.createNewFile();
                PrintWriter newGameHistoryFile = new PrintWriter(file);
                for (String setup : board.getInitialSetup()){
                    newGameHistoryFile.println(setup);
                }
                for(String moveNotation : board.getMovesHistory())
                    newGameHistoryFile.println(moveNotation);
                newGameHistoryFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (answer) return new SetupGameView(mainWindow).display();
        }

        /*BoardView + GameView*/
        BoardView boardView = new BoardView(mainWindow, board, goldenPlayerTime, silverPlayerTime);
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

        Label timerLabel = new Label("0:00");
        timerLabel.setPadding(new Insets(10));
        timerLabel.setAlignment(Pos.BOTTOM_CENTER);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (player.equals("Golden player")) {
                if (board.getCurrentPlayer() == board.getGoldenPlayer()) {
                    goldenPlayerTime++;
                }
            } else if (player.equals("Silver player")) {
                if (board.getCurrentPlayer() == board.getSilverPlayer()) {
                    silverPlayerTime++;
                }
            }
            int playerTime = player.equals("Golden player") ? goldenPlayerTime : silverPlayerTime;

            int minutes = playerTime / 60;
            int seconds = playerTime % 60;

            String timeText = String.format("%d:%02d", minutes, seconds);
            timerLabel.setText(timeText);
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        if (player.equals("Golden player")) {
            if (board.getCurrentPlayer().toString().equals("g")) {
                timeline.play();
            } else {
                timeline.pause();
            }
        } else if (player.equals("Silver player")) {
            if (board.getCurrentPlayer().toString().equals("s")) {
                timeline.play();
            } else {
                timeline.pause();
            }
        }

        VBox vBox = new VBox(label, timerLabel);
        vBox.setAlignment(Pos.TOP_CENTER);
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
        Label moveHistoryLabel = new Label();
        moveHistoryLabel.setPadding(new Insets(1));

        ListView<String> moveHistoryListView = new ListView<>();
        moveHistoryListView.setPrefWidth(100);
        moveHistoryListView.setPrefHeight(10);

        ObservableList<String> moveHistory = FXCollections.observableArrayList
                (board.getMovesHistory().subList(Math.max(0, board.getMovesHistory().size() - 1), board.getMovesHistory().size()));
        moveHistoryListView.setItems(moveHistory);

        HBox moveHistoryBox = new HBox(moveHistoryLabel, moveHistoryListView);
        moveHistoryBox.setAlignment(Pos.CENTER);
        moveHistoryBox.setSpacing(10);
        moveHistoryBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        HBox botMenu = new HBox(previousMove, undoMove, nextMove, moveHistoryBox);
        botMenu.setAlignment(Pos.CENTER);
        botMenu.setSpacing(10);
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
            if(answer) {
                mainWindow.setScene(mainSceneView.display());
            }
        });
        HBox topMenu = new HBox(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(4));
        return topMenu;
    }

}
