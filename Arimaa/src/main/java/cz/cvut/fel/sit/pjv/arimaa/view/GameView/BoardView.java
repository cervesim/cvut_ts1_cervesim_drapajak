package cz.cvut.fel.sit.pjv.arimaa.view.GameView;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardView{
    public Board board;
    public static int clickCount = 0;
    public static Square firstClickedSquare;
    public static Square secondClickedSquare;
    public static Square thirdClickedSquare;
    public BoardView(Board board) {
        this.board = board;
    }

    public GridPane display() {
        GridPane boardGridPane = new GridPane();
        boardGridPane.setPadding(new Insets(10,10,10,10));
        boardGridPane.setGridLinesVisible(true);
        boardGridPane.setHgap(1);
        boardGridPane.setVgap(1);
        boardGridPane.setAlignment(Pos.CENTER);

        int cellCounter = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                SquareView squareView = new SquareView(cellCounter, board);
                StackPane stackPane = squareView.setSquare();
                boardGridPane.add(stackPane, col, row);
                cellCounter++;
            }
        }
        return boardGridPane;
    }

}

