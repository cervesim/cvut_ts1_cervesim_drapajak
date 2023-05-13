package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public class SimpleMove extends Move /*TODO*/ {
    public SimpleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
        return null;
    }
}
