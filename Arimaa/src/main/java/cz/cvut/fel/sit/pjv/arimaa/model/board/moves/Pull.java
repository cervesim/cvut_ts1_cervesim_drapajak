package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public class Pull extends Move{
    public Pull(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
