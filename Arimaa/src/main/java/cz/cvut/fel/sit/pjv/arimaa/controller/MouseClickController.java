package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.GameUtils.isTrapSquare;

public class MouseClickController extends GameView {
    StackPane stackPane;
    Rectangle cell;
    int squarePosition;
    public MouseClickController(Stage mainWindow, Board board, Timer timer, StackPane stackPane, Rectangle cell, int squarePosition) {
        super(mainWindow, board, timer);
        this.stackPane = stackPane;
        this.cell = cell;
        this.squarePosition = squarePosition;
        execute();
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
    private void checkAndExecute(Move move){
        if (board.getCurrentPlayer().isMoveLegal(move)) {
            GameView gameView = new GameView(mainWindow, move.execute(), timer);
            mainWindow.setScene(gameView.display());
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else {
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        }
    }
    private void rightClickControls(MouseEvent e){
        if (e.getClickCount() == 1){
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else if (e.getClickCount() == 3 && board.getMoveCount() >= 1) {
            Move skipTurnsMove = new SkipTurnsMove(board, null, 0);
            GameView gameView = new GameView(mainWindow, skipTurnsMove.execute(), timer);
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
                checkAndExecute(move);
            } else {
                cell.setFill(Color.RED);
            }
        } else if (clickCount == 3) {
            BoardView.thirdClickedSquare = board.getSquare(squarePosition);
            if (!BoardView.thirdClickedSquare.isSquareOccupied()) {
                Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                        BoardView.secondClickedSquare.getPieceOnSquare(),
                        BoardView.thirdClickedSquare.getSquareLocation());
                checkAndExecute(move);

            } else {
                cell.setFill(Color.RED);
            }
            BoardView.clickCount = 0;
        }

    }
}
