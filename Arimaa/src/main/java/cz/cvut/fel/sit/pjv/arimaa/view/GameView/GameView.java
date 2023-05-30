package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.ConfirmBoxView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameView extends SetupGameView{
    Board board;
    public static boolean gameEnded;
    public GameView(Stage mainWindow, Board board) {
        super(mainWindow);
        this.board = board;
        GameView.gameEnded = board.gameEnded;
    }
    public GameView(Stage mainWindow) {
        super(mainWindow);
        this.board = Board.createTestBoard();
        GameView.gameEnded = false;
    }

    public Scene display(){
        /*TOP menu*/
        HBox topMenu = makeTopMenu();

        if (GameView.gameEnded){
            String text = "Game ended, " + board.hasWon().toString() + " is the winner. Do you want to play again??";
            boolean answer = ConfirmBoxView.display("Game ended", text);
            if (answer) {
                SetupGameView setupGameView = new SetupGameView(mainWindow);
                return setupGameView.display();
            }/*TODO block mouse controller*/
        }
        topMenu.setAlignment(Pos.TOP_RIGHT);
        /*TOP menu*/

        /*BoardView + GameView*/
        BoardView boardView = new BoardView(mainWindow, board);
        BorderPane borderPane = new BorderPane(boardView.displayBoard(), topMenu, null, null, null);
        /*BoardView + GameView*/
        return setScene(borderPane);
    }


}
