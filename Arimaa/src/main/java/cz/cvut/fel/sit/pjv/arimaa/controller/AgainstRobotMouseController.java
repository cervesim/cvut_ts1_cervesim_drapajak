package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.GameView.GameView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.GameUtils.isTrapSquare;

public class AgainstRobotMouseController extends MouseClickController{
    /**
     * Extends MouseClickController and is used for controlling the random moves from robot and blocking gold player from moving with the robots silver pieces.
     * @param mainWindow to display new build board after
     * @param board to get board data from
     * @param timer so the time doesn't change after displaying new gameView
     * @param stackPane there is mouse click event controller bind onto that
     * @param cell for cell color
     * @param squarePosition for me to know where the square is when I click it
     */
    public AgainstRobotMouseController(Stage mainWindow, Board board, Timer timer, StackPane stackPane, Rectangle cell, int squarePosition) {
        super(mainWindow, board, timer, stackPane, cell, squarePosition);
    }

    @Override
    void rightClickControls(MouseEvent e) {
        if (e.getClickCount() == 1) {
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else if (e.getClickCount() == 2 && board.getCurrentPlayer().getAlliance().isSilver()) {
            ArrayList<Move> arrayList = board.getSilverPlayer().getLegalMoves();
            Random random = new Random();
            int randomIndex = random.nextInt(arrayList.size());
            Move newMove = arrayList.get(randomIndex);
            GameView gameView = new GameView(mainWindow, newMove.execute(), timer);
            mainWindow.setScene(gameView.display());
            BoardView.clickCount = 0;
            cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
        } else if (e.getClickCount() == 3 && board.getMoveCount() >= 1 && board.getCurrentPlayer().getAlliance().isGolden()) {
            Move skipTurnsMove = new SkipTurnsMove(board, null, 0);
            GameView gameView = new GameView(mainWindow, skipTurnsMove.execute(), timer);
            mainWindow.setScene(gameView.display());
        }
    }
    @Override
    void leftClickControls(){
        int clickCount = ++BoardView.clickCount;
        if (clickCount == 1) {
            BoardView.firstClickedSquare = board.getSquare(squarePosition);
            if (BoardView.firstClickedSquare.isSquareOccupied()) {
                cell.setFill(Color.RED);
            }else{
                BoardView.clickCount = 0;
                cell.setFill(isTrapSquare(squarePosition) ? Color.BLACK : Color.WHITE);
            }
        } else if (clickCount == 2) {
            BoardView.secondClickedSquare = board.getSquare(squarePosition);
            Alliance colorOfPieceOnFirstSquare = BoardView.firstClickedSquare.getPieceOnSquare().getPieceColor();
            if (!BoardView.secondClickedSquare.isSquareOccupied() && colorOfPieceOnFirstSquare.isGolden()) {
                Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                        null, BoardView.secondClickedSquare.getSquareLocation());
                checkAndExecute(move);
            } else {cell.setFill(Color.RED);}
        } else if (clickCount == 3){
            BoardView.thirdClickedSquare = board.getSquare(squarePosition);
            if (!BoardView.thirdClickedSquare.isSquareOccupied() && board.getCurrentPlayer().getAlliance().isGolden()) {
                Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                        BoardView.secondClickedSquare.getPieceOnSquare(),
                        BoardView.thirdClickedSquare.getSquareLocation());
                checkAndExecute(move);
            }
            BoardView.clickCount = 0;
        }

    }
}
