package cz.cvut.fel.sit.pjv.arimaa.view.GameView;

import cz.cvut.fel.sit.pjv.arimaa.controller.AgainstRobotMouseController;
import cz.cvut.fel.sit.pjv.arimaa.controller.MouseClickController;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Timer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.GameUtils.isTrapSquare;

public class SquareView extends GameView{
    int squarePosition;
    public SquareView(Stage mainWindow, Board board, Timer timer, int squarePosition) {
        super(mainWindow, board, timer);
        this.squarePosition = squarePosition;
    }
    public StackPane setSquare() {
        Rectangle cell = new Rectangle(50, 50);
        Square currentSquare = board.getSquare(squarePosition);
        cell.setFill(isTrapSquare(squarePosition)
                ? Color.BLACK : Color.WHITE);

        if (currentSquare.isSquareOccupied()) {
            Piece pieceOnSquare = currentSquare.getPieceOnSquare();
            ImageView imageView = new ImageView(PieceView.getPieceImage(pieceOnSquare.getPieceType(), pieceOnSquare.getPieceColor()));
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);

            StackPane stackPane = new StackPane(cell, imageView);

            if (!board.gameEnded && !BoardView.inViewMode){
                if (GameView.againstComputer){
                    new AgainstRobotMouseController(mainWindow,board, timer, stackPane, cell, squarePosition);
                } else new MouseClickController(mainWindow, board, timer, stackPane, cell,  squarePosition);

            }
            return stackPane;
        }

        StackPane stackPane = new StackPane(cell);
        if (GameView.againstComputer) new AgainstRobotMouseController(mainWindow,board, timer, stackPane, cell, squarePosition);
        else new MouseClickController(mainWindow, board, timer, stackPane, cell,  squarePosition);
        return stackPane;
    }


}
