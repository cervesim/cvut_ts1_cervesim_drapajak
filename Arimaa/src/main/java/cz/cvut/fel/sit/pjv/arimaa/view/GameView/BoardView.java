package cz.cvut.fel.sit.pjv.arimaa.view.GameView;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.GameUtils;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BoardView extends GameView{
    public static int clickCount = 0;
    public static Square firstClickedSquare;
    public static Square secondClickedSquare;
    public static Square thirdClickedSquare;
    public BoardView(Stage mainWindow, Board board, GameView previousBoard, int goldenPlayerTime, int silverPlayerTime) {
        super(mainWindow, board, previousBoard, goldenPlayerTime , silverPlayerTime);
        this.goldenPlayerTime = goldenPlayerTime;
        this.silverPlayerTime = silverPlayerTime;
    }
    public GridPane displayBoard() {
        GridPane boardGridPane = new GridPane();
        GameUtils.setBoardGridPane(boardGridPane);

        int cellCounter = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                SquareView squareView = new SquareView(mainWindow, board, previousBoard, goldenPlayerTime, silverPlayerTime, cellCounter);
                StackPane stackPane = squareView.setSquare();
                boardGridPane.add(stackPane, col, row);
                cellCounter++;
            }
        }
        return boardGridPane;
    }

}

