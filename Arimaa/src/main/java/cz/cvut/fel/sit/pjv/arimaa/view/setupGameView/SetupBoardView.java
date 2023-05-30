package cz.cvut.fel.sit.pjv.arimaa.view.setupGameView;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.view.utils.GameUtils;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Collection;

public class SetupBoardView {
    Stage mainWindow;

    public Board board;
    Collection<Piece> goldenPlayerPieces;
    Collection<Piece> silverPlayerPieces;
    public SetupBoardView(Stage mainWindow, Board board, Collection<Piece> goldenPlayerPieces, Collection<Piece> silverPlayerPieces) {
        this.mainWindow = mainWindow;
        this.board = board;
        this.goldenPlayerPieces = goldenPlayerPieces;
        this.silverPlayerPieces = silverPlayerPieces;
    }

    public GridPane display() {
        GridPane boardGridPane = new GridPane();
        GameUtils.setBoardGridPane(boardGridPane);

        int cellCounter = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                SetupSquareView setupSquareView = new SetupSquareView(cellCounter, mainWindow,  board, goldenPlayerPieces, silverPlayerPieces);
                StackPane stackPane = setupSquareView.setSquare();
                boardGridPane.add(stackPane, col, row);
                cellCounter++;
            }
        }
        return boardGridPane;
    }

}