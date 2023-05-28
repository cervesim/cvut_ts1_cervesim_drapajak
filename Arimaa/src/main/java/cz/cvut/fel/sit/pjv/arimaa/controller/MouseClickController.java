package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MouseClickController {
    Stage mainWindow;
    StackPane stackPane;
    Rectangle cell;
    Board board;
    int squarePosition;
    public MouseClickController(Stage mainWindow, StackPane stackPane, Rectangle cell, Board board, int squarePosition) {
        this.mainWindow = mainWindow;
        this.stackPane = stackPane;
        this.cell = cell;
        this.board = board;
        this.squarePosition = squarePosition;
    }
    public void execute(){
        stackPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                leftClickControls();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                rightClickControls(e);
            }
        });
    }
    private void checkAndExecute(Move move, Rectangle cell){
        if (board.getCurrentPlayer().isMoveLegal(move)) {
            GameView gameView = new GameView(mainWindow, move.execute());
            mainWindow.setScene(gameView.display());
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else {
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        }
    }
    private boolean isTrapSquare(int squarePosition){
        return (squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45);
    }
    private void rightClickControls(MouseEvent e){
        if (e.getClickCount() == 1){
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else if (e.getClickCount() == 3 && board.getMoveCount() >= 1) {
            System.out.println("round skipped"); /*TODO delete print*/
            Move skipTurnsMove = new SkipTurnsMove(board, null, 0);
            GameView gameView = new GameView(mainWindow, skipTurnsMove.execute());
            mainWindow.setScene(gameView.display());
        }
    }
    private void leftClickControls(){
        int clickCount = ++BoardView.clickCount;
        if (clickCount == 1) {
            BoardView.firstClickedSquare = board.getSquare(squarePosition);
            if (!BoardView.firstClickedSquare.isSquareOccupied()) {
                BoardView.clickCount = 0;
                cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
            } else {
                cell.setFill(Color.RED);
            }
        } else if (clickCount == 2) {
            BoardView.secondClickedSquare = board.getSquare(squarePosition);
            if (!BoardView.secondClickedSquare.isSquareOccupied()) {
                Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                        null, BoardView.secondClickedSquare.getSquareLocation());
                checkAndExecute(move, cell);
            } else {
                cell.setFill(Color.RED);
            }
        } else if (clickCount == 3) {
            BoardView.thirdClickedSquare = board.getSquare(squarePosition);
            if (!BoardView.thirdClickedSquare.isSquareOccupied()) {
                Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                        BoardView.secondClickedSquare.getPieceOnSquare(),
                        BoardView.thirdClickedSquare.getSquareLocation());
                checkAndExecute(move, cell);
            } else {
                cell.setFill(Color.RED);
            }
            BoardView.clickCount = 0;
        }

    }
}
