package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import cz.cvut.fel.sit.pjv.arimaa.view.gameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.gameView.GameView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.NotationUtils.isTrapSquare;

public class MouseClickController extends GameView {
    private static final Logger logger = Logger.getLogger(MouseClickController.class.getName());
    StackPane stackPane;
    Rectangle cell;
    int squarePosition;

    /**
     * This class sets mouse click event controller to every square on board and tells what to do after each click.
     * After right combination of clicks on the right squares the move is created depending on which player clicked.
     * @param mainWindow to display new build board after
     * @param board to get board data from
     * @param timer so the time doesn't change after displaying new gameView
     * @param stackPane there is mouse click event controller bind onto that
     * @param cell for cell color
     * @param squarePosition for me to know where the square is when I click it
     */
    public MouseClickController(Stage mainWindow, Board board, Timer timer, StackPane stackPane, Rectangle cell, int squarePosition) {
        super(mainWindow, board, timer);
        this.stackPane = stackPane;
        this.cell = cell;
        this.squarePosition = squarePosition;
        execute();
    }

    /**
     * After executing the mouse click event controller is set onto square.
     * The left and right controls are used to determine what happens after each click.
     */
    public void execute(){
        stackPane.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                leftClickControls();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                rightClickControls(e);
            }
        });
    }
    void checkAndExecute(Move move){
        if (board.getCurrentPlayer().isMoveLegal(move)) {
            GameView gameView = new GameView(mainWindow, move.execute(), timer);
            mainWindow.setScene(gameView.display());
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else {
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        }
    }
    void rightClickControls(MouseEvent e){
        if (e.getClickCount() == 1){
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else if (e.getClickCount() == 3 && board.getMoveCount() >= 1) {
            Move skipTurnsMove = new SkipTurnsMove(board, null, 0);
            GameView gameView = new GameView(mainWindow, skipTurnsMove.execute(), timer);
            mainWindow.setScene(gameView.display());
        }
    }
    void leftClickControls(){
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
            }
            BoardView.clickCount = 0;
        }

    }
}
