package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.MainSceneView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.SettingsStageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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


public class GameView{
    protected Stage mainWindow;
    public boolean fileSaved = false;
    protected Board board;
    public boolean gameEnded;
    public Timer timer;
    protected Label goldenPlayerTimer;
    protected Label silverPlayerTimer;
    public static boolean inViewMode;
    public static int howFarInPast = 0;
    public GameView(Stage mainWindow) {
        this.mainWindow = mainWindow;
        GameView.howFarInPast = 0;
        this.board = Board.createTestBoard();
        this.gameEnded = false;
        this.timer = new Timer(board);
        goldenPlayerTimer = timer.goldenPlayerTimer;
        silverPlayerTimer = timer.silverPlayerTimer;
    }
    public GameView(Stage mainWindow, Board board) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.gameEnded = board.gameEnded;
        this.timer = new Timer(board);
        this.goldenPlayerTimer = timer.goldenPlayerTimer;
        this.silverPlayerTimer = timer.silverPlayerTimer;
    }
    public GameView(Stage mainWindow, Board board, Timer timer) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.gameEnded = board.gameEnded;
        this.timer = timer;
        timer.setPlayers(board);
        this.goldenPlayerTimer = timer.goldenPlayerTimer;
        this.silverPlayerTimer = timer.silverPlayerTimer;
    }
    public Scene display() {
        if (!inViewMode || !fileSaved){
            if (this.gameEnded){
                String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
                boolean answer = ConfirmBoxView.display("Game ended", text);

                System.out.println(board.getInitialSetup().toString());/*TODO destroy it*/
                System.out.println(board.getMovesHistory().toString());/*TODO destroy it*/

                SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                Date date = new Date();

                File file = new File("src/main/ArimaaGameHistory/" + formatter.format(date) + ".txt");
                try {
                    boolean successful = file.createNewFile();
                    if (successful){
                        PrintWriter newGameHistoryFile = new PrintWriter(file);
                        for (String setup : board.getInitialSetup()){
                            newGameHistoryFile.println(setup);
                        }
                        for(String moveNotation : board.getMovesHistory())
                            newGameHistoryFile.println(moveNotation);
                        newGameHistoryFile.close();
                    }
                    fileSaved = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (answer) return new SetupGameView(mainWindow).display();
            }
        }

        /*BoardView + GameView*/
        BoardView boardView = new BoardView(mainWindow, board, timer);
        BorderPane borderPane = new BorderPane(boardView.displayBoard(),
                makeTopMenu(),
                makeLeftAndRightMenu(board.getGoldenPlayer()),
                makeBottomMenu(),
                makeLeftAndRightMenu(board.getSilverPlayer())
        );
        /*BoardView + GameView*/

        return new Scene(borderPane, 720, 600);
    }
    private VBox makeLeftAndRightMenu(Player player) {
        Label label = new Label(player.equals(board.getGoldenPlayer()) ? "Golden Player" : "Silver Player");
        Label timerLabel = player.equals(board.getGoldenPlayer()) ? goldenPlayerTimer : silverPlayerTimer;

        VBox vBox = new VBox(label, timerLabel);
        vBox.setAlignment(Pos.TOP_CENTER);
        return vBox;
    }
    private HBox makeBottomMenu(){
        Button previousMove = new Button("Previous move");
        previousMove.setOnAction(e -> {
            int backToThePast = ((board.getMovesHistory().size() + howFarInPast));
            if (backToThePast != 0){
                GameView.howFarInPast--;
                GameView.inViewMode = howFarInPast != 0;
                String pieceThatIsReturningToPast = board.getMovesHistory().get(backToThePast - 1);
                Move viewMove = board.decodeMove(pieceThatIsReturningToPast, true);
                GameView gameView = new GameView(mainWindow, viewMove.execute());
                mainWindow.setScene(gameView.display());
            }
        });
        Button nextMove = new Button("Next move");
        nextMove.setOnAction(e -> {

            int backToTheFuture = (board.getMovesHistory().size() + howFarInPast);
            if (howFarInPast != 0){
                howFarInPast++;
                GameView.inViewMode = howFarInPast != 0;
                fileSaved = true;
                String pieceThatIsReturningToFuture = board.getMovesHistory().get(backToTheFuture);
                Move viewMove = board.decodeMove(pieceThatIsReturningToFuture, false);
                GameView gameView = new GameView(mainWindow, viewMove.execute());
                mainWindow.setScene(gameView.display());
            }
        });
        Button undoMove = new Button("Undo move");
        undoMove.setOnAction(e -> {
           if (!GameView.inViewMode || !this.gameEnded){
               int backToThePast = ((board.getMovesHistory().size() + howFarInPast));
               if (backToThePast != 0){
                   if (this.gameEnded) {GameView.inViewMode = true;} else  GameView.inViewMode = (howFarInPast != 0);
                   String pieceThatIsReturningToPast = board.getMovesHistory().remove(backToThePast - 1);
                   Move viewMove = board.decodeMove(pieceThatIsReturningToPast, true);
                   GameView gameView = new GameView(mainWindow, viewMove.execute());
                   mainWindow.setScene(gameView.display());
               }
           }
        });
        Label moveHistoryLabel = new Label();
        moveHistoryLabel.setPadding(new Insets(1));

        ListView<String> moveHistoryListView = new ListView<>();
        moveHistoryListView.setPrefWidth(100);
        moveHistoryListView.setPrefHeight(10);

        ObservableList<String> moveHistory = FXCollections.observableArrayList
                (board.getMovesHistory().subList(Math.max(0, board.getMovesHistory().size() - 10), board.getMovesHistory().size()));
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
                GameView.inViewMode = false;
                GameView.howFarInPast = 0;
            }
        });
        HBox topMenu = new HBox(gameSettingsButton, exitButton);
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.setPadding(new Insets(4));
        return topMenu;
    }
}
