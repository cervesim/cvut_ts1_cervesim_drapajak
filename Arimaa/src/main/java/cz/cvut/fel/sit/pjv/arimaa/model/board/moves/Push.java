package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

/**
 *
 */
public class Push extends Move /*TODO*/{
    public Push(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
