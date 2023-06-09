package cz.cvut.fel.sit.pjv.arimaa.view.gameView;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.GameUtils;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BoardView extends GameView{
    public static int clickCount = 0;
    public static Square firstClickedSquare;
    public static Square secondClickedSquare;
    public static Square thirdClickedSquare;
    public BoardView(Stage mainWindow, Board board, Timer timer) {
        super(mainWindow, board, timer);
    }
    public GridPane displayBoard() {
        GridPane boardGridPane = new GridPane();
        GameUtils.setBoardGridPane(boardGridPane);

        int cellCounter = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                SquareView squareView = new SquareView(mainWindow, board, timer, cellCounter);
                StackPane stackPane = squareView.setSquare();
                boardGridPane.add(stackPane, col, row);
                cellCounter++;
            }
        }
        return boardGridPane;
    }

}

